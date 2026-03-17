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
