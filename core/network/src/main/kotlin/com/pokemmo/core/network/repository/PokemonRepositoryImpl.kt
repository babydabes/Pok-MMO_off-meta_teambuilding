package com.pokemmo.core.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.pokemmo.core.common.extensions.toFtsQuery
import com.pokemmo.core.database.dao.MoveDao
import com.pokemmo.core.database.dao.PokemonDao
import com.pokemmo.core.database.dao.TypeDao
import com.pokemmo.core.database.entity.PokemonEntity
import com.pokemmo.core.domain.model.*
import com.pokemmo.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val moveDao: MoveDao,
    private val typeDao: TypeDao,
) : PokemonRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        prefetchDistance = 10,
        enablePlaceholders = false,
    )

    override fun getPagedPokemon(tier: PokemmoTier?): Flow<PagingData<Pokemon>> =
        Pager(pagingConfig) {
            if (tier != null) pokemonDao.getByTierPaged(tier.label)
            else pokemonDao.getAllPaged()
        }.flow.map { paging -> paging.map { it.toDomain() } }

    override fun searchPokemon(query: String, typeFilter: String?): Flow<PagingData<Pokemon>> {
        val ftsQuery = query.toFtsQuery()
        return Pager(pagingConfig) {
            when {
                ftsQuery.isBlank() && typeFilter != null ->
                    pokemonDao.getByTypePaged(typeFilter)
                ftsQuery.isNotBlank() && typeFilter != null ->
                    pokemonDao.searchFtsFilteredByType(ftsQuery, typeFilter)
                ftsQuery.isNotBlank() ->
                    pokemonDao.searchFtsPaged(ftsQuery)
                else ->
                    pokemonDao.getAllPaged()
            }
        }.flow.map { paging -> paging.map { it.toDomain() } }
    }

    override fun searchByTaxonomyTags(tagIds: List<Int>): Flow<PagingData<Pokemon>> =
        Pager(pagingConfig) {
            pokemonDao.getByTaxonomyTagsPaged(tagIds)
        }.flow.map { paging -> paging.map { it.toDomain() } }

    override suspend fun getPokemonById(id: Int): Pokemon? =
        pokemonDao.getById(id)?.toDomain()

    override suspend fun getPokemonByIds(ids: List<Int>): List<Pokemon> =
        pokemonDao.getByIds(ids).map { it.toDomain() }

    override suspend fun getMovesForPokemon(pokemonId: Int): List<Move> {
        val typeList = typeDao.getAllTypes()
        val typeMap = typeList.associateBy { it.name }
        return moveDao.getMovesForPokemon(pokemonId).map { entity ->
            val type = typeMap[entity.typeName]
                ?: Type(0, entity.typeName, "#A8A878")
            entity.toDomain(type)
        }
    }

    override suspend fun count(): Int = pokemonDao.count()

    override suspend fun syncFromNetwork(): Result<Unit> = Result.success(Unit)
}

// ---------------------------------------------------------------------------
// Extension mappers
// ---------------------------------------------------------------------------

private fun PokemonEntity.toDomain(): Pokemon {
    val types = buildList {
        add(Type(0, type1, "#A8A878"))
        if (type2.isNotBlank()) add(Type(0, type2, "#A8A878"))
    }
    val abilities = buildList {
        if (ability1.isNotBlank()) add(Ability(ability1))
        if (ability2.isNotBlank()) add(Ability(ability2))
        if (hiddenAbility.isNotBlank()) add(Ability(hiddenAbility, "Hidden Ability"))
    }
    return Pokemon(
        id = id,
        name = name,
        displayName = displayName,
        generation = generation,
        stats = BaseStats(baseHp, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpeed),
        types = types,
        abilities = abilities,
        spriteUrl = spriteUrl,
        spriteShinyUrl = spriteShinyUrl,
        tier = PokemmoTier.fromString(pokemmoTier),
        isLegendary = isLegendary,
        isMythical = isMythical,
        height = height,
        weight = weight,
        flavorText = flavorText,
    )
}

private fun com.pokemmo.core.database.entity.MoveEntity.toDomain(type: Type): Move = Move(
    id = id,
    name = name,
    displayName = displayName,
    type = type,
    category = when (category) {
        "Physical" -> MoveCategory.Physical
        "Special"  -> MoveCategory.Special
        else       -> MoveCategory.Status
    },
    power = power,
    accuracy = accuracy,
    pp = pp,
    priority = priority,
    description = description,
)
