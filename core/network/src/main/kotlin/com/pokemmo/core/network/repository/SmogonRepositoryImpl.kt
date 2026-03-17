package com.pokemmo.core.network.repository

import com.pokemmo.core.database.dao.SmogonSetDao
import com.pokemmo.core.domain.model.Nature
import com.pokemmo.core.domain.model.SmogonSet
import com.pokemmo.core.domain.model.StatSpread
import com.pokemmo.core.domain.repository.SmogonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmogonRepositoryImpl @Inject constructor(
    private val smogonSetDao: SmogonSetDao,
) : SmogonRepository {

    override suspend fun getSetsForPokemon(pokemonId: Int, tier: String?): List<SmogonSet> {
        val entities = if (tier != null)
            smogonSetDao.getSetsForPokemonByTier(pokemonId, tier)
        else
            smogonSetDao.getSetsForPokemon(pokemonId)

        return entities.map { e ->
            SmogonSet(
                id = e.id,
                pokemonId = e.pokemonId,
                setName = e.setName,
                tier = e.tier,
                nature = Nature.fromName(e.nature),
                ability = e.abilityName,
                item = e.itemName,
                moves = listOf(e.move1, e.move2, e.move3, e.move4).filter { it.isNotBlank() },
                evs = StatSpread(e.evHp, e.evAtk, e.evDef, e.evSpAtk, e.evSpDef, e.evSpeed),
                description = e.description,
                usageRank = e.usageRank,
            )
        }
    }

    override suspend fun syncSmogonSets(): Result<Unit> = Result.success(Unit)
}
