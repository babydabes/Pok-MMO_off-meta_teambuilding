package com.pokemmo.core.network.repository

import com.pokemmo.core.database.dao.MoveDao
import com.pokemmo.core.database.dao.PokemonDao
import com.pokemmo.core.database.dao.TeamDao
import com.pokemmo.core.database.dao.TypeDao
import com.pokemmo.core.database.entity.TeamEntity
import com.pokemmo.core.database.entity.TeamMemberEntity
import com.pokemmo.core.domain.model.*
import com.pokemmo.core.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao,
    private val pokemonDao: PokemonDao,
    private val moveDao: MoveDao,
    private val typeDao: TypeDao,
) : TeamRepository {

    override fun observeAllTeams(): Flow<List<Team>> =
        teamDao.observeAllTeamsWithMembers().map { list ->
            list.map { it.team.toDomain(it.members) }
        }

    override fun observeTeam(teamId: Long): Flow<Team?> =
        teamDao.observeTeamWithMembers(teamId).map { teamWithMembers ->
            teamWithMembers?.let { it.team.toDomain(it.members) }
        }

    override suspend fun createTeam(name: String, format: String): Long =
        teamDao.insertTeam(TeamEntity(name = name, format = format))

    override suspend fun saveTeam(team: Team) {
        val entity = TeamEntity(
            id = team.id,
            name = team.name,
            format = team.format,
            notes = team.notes,
            updatedAt = System.currentTimeMillis(),
        )
        teamDao.updateTeam(entity)
        teamDao.clearAllMembers(team.id)
        team.members.forEachIndexed { slot, member ->
            if (member != null) {
                teamDao.upsertMember(member.toEntity(team.id))
            }
        }
    }

    override suspend fun addMember(teamId: Long, pokemonId: Int, slot: Int): Result<Unit> {
        return runCatching {
            val pokemon = pokemonDao.getById(pokemonId)
                ?: error("Pokemon $pokemonId not found in database")
            val entity = TeamMemberEntity(
                teamId = teamId,
                pokemonId = pokemonId,
                slot = slot,
                abilityName = pokemon.ability1,
            )
            teamDao.upsertMember(entity)
            teamDao.touchTeam(teamId)
        }
    }

    override suspend fun updateMember(teamId: Long, member: TeamMember): Result<Unit> =
        runCatching {
            teamDao.upsertMember(member.toEntity(teamId))
            teamDao.touchTeam(teamId)
        }

    override suspend fun removeMember(teamId: Long, slot: Int) {
        teamDao.removeMemberBySlot(teamId, slot)
        teamDao.touchTeam(teamId)
    }

    override suspend fun archiveTeam(teamId: Long) = teamDao.archiveTeam(teamId)

    override suspend fun importFromShowdown(paste: String): Result<Team> = runCatching {
        val members = ShowdownPasteParser.parse(paste)
        val resolvedMembers = members.mapNotNull { partial ->
            val pokemon = pokemonDao.getById(partial.pokemonId) ?: return@mapNotNull null
            partial
        }
        val teamId = teamDao.insertTeam(
            TeamEntity(name = "Imported Team", format = "PokéMMO-OU")
        )
        resolvedMembers.forEach { partial ->
            teamDao.upsertMember(
                TeamMemberEntity(
                    teamId = teamId,
                    pokemonId = partial.pokemonId,
                    slot = partial.slot,
                    nickname = partial.nickname,
                    natureName = partial.nature,
                    abilityName = partial.ability,
                    itemName = partial.item,
                    evHp = partial.evs.hp,
                    evAtk = partial.evs.atk,
                    evDef = partial.evs.def,
                    evSpAtk = partial.evs.spAtk,
                    evSpDef = partial.evs.spDef,
                    evSpeed = partial.evs.speed,
                )
            )
        }
        val members2 = teamDao.getMembersForTeam(teamId)
        teamDao.getTeamById(teamId)!!.toDomain(members2)
    }

    override fun exportToShowdown(team: Team): String =
        team.members.filterNotNull().joinToString("\n\n") { member ->
            buildString {
                val nameStr = if (member.nickname.isNotBlank())
                    "${member.nickname} (${member.pokemon.displayName})"
                else member.pokemon.displayName
                appendLine(if (member.item.isNotBlank()) "$nameStr @ ${member.item}" else nameStr)
                appendLine("Ability: ${member.ability.name}")
                val evLine = member.evs.toShowdownString()
                if (evLine.isNotEmpty()) appendLine("EVs: $evLine")
                appendLine("${member.nature.name} Nature")
                member.moves.filterNotNull().forEach { appendLine("- ${it.displayName}") }
            }.trimEnd()
        }

    // ---------------------------------------------------------------------------
    // Mappers
    // ---------------------------------------------------------------------------

    private suspend fun TeamEntity.toDomain(memberEntities: List<TeamMemberEntity>): Team {
        val slots = Array<TeamMember?>(6) { null }
        val typeMap = typeDao.getAllTypes().associateBy { it.name }
        memberEntities.forEach { entity ->
            val pokemon = pokemonDao.getById(entity.pokemonId) ?: return@forEach
            val moveIds = listOf(entity.move1Id, entity.move2Id, entity.move3Id, entity.move4Id)
                .filter { it != 0 }
            val movesMap = if (moveIds.isNotEmpty())
                moveDao.getByIds(moveIds).associateBy { it.id }
            else emptyMap()

            val domainPokemon = pokemon.toDomain()
            slots[entity.slot.coerceIn(0, 5)] = TeamMember(
                slot = entity.slot,
                pokemon = domainPokemon,
                nickname = entity.nickname,
                level = entity.level,
                item = entity.itemName,
                ability = Ability(entity.abilityName),
                nature = Nature.fromName(entity.natureName),
                moves = listOf(entity.move1Id, entity.move2Id, entity.move3Id, entity.move4Id).map { id ->
                    if (id == 0) null
                    else movesMap[id]?.let { me ->
                        val type = typeMap[me.typeName] ?: Type(0, me.typeName, "#A8A878")
                        me.toDomain(type)
                    }
                },
                evs = StatSpread(entity.evHp, entity.evAtk, entity.evDef, entity.evSpAtk, entity.evSpDef, entity.evSpeed),
                ivs = StatSpread(entity.ivHp, entity.ivAtk, entity.ivDef, entity.ivSpAtk, entity.ivSpDef, entity.ivSpeed),
                isShiny = entity.isShiny,
            )
        }
        return Team(
            id = id,
            name = name,
            format = format,
            members = slots.toList(),
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private fun TeamMember.toEntity(teamId: Long): TeamMemberEntity = TeamMemberEntity(
        teamId = teamId,
        pokemonId = pokemon.id,
        slot = slot,
        nickname = nickname,
        level = level,
        itemName = item,
        abilityName = ability.name,
        natureName = nature.name,
        move1Id = moves.getOrNull(0)?.id ?: 0,
        move2Id = moves.getOrNull(1)?.id ?: 0,
        move3Id = moves.getOrNull(2)?.id ?: 0,
        move4Id = moves.getOrNull(3)?.id ?: 0,
        evHp = evs.hp, evAtk = evs.atk, evDef = evs.def,
        evSpAtk = evs.spAtk, evSpDef = evs.spDef, evSpeed = evs.speed,
        ivHp = ivs.hp, ivAtk = ivs.atk, ivDef = ivs.def,
        ivSpAtk = ivs.spAtk, ivSpDef = ivs.spDef, ivSpeed = ivs.speed,
        isShiny = isShiny,
    )
}

// -------------------------------------------------------------------------
// Partial member used during Showdown import before DB lookup is complete
// -------------------------------------------------------------------------
data class PartialMember(
    val slot: Int,
    val pokemonId: Int,
    val nickname: String,
    val nature: String,
    val ability: String,
    val item: String,
    val evs: StatSpread,
)
