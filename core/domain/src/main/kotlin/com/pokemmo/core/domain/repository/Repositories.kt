package com.pokemmo.core.domain.repository

import androidx.paging.PagingData
import com.pokemmo.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPagedPokemon(tier: PokemmoTier? = null): Flow<PagingData<Pokemon>>
    fun searchPokemon(query: String, typeFilter: String? = null): Flow<PagingData<Pokemon>>
    fun searchByTaxonomyTags(tagIds: List<Int>): Flow<PagingData<Pokemon>>
    suspend fun getPokemonById(id: Int): Pokemon?
    suspend fun getPokemonByIds(ids: List<Int>): List<Pokemon>
    suspend fun getMovesForPokemon(pokemonId: Int): List<Move>
    suspend fun count(): Int
    suspend fun syncFromNetwork(): Result<Unit>
}

interface TeamRepository {
    fun observeAllTeams(): Flow<List<Team>>
    fun observeTeam(teamId: Long): Flow<Team?>
    suspend fun createTeam(name: String, format: String): Long
    suspend fun saveTeam(team: Team)
    suspend fun addMember(teamId: Long, pokemonId: Int, slot: Int): Result<Unit>
    suspend fun updateMember(teamId: Long, member: TeamMember): Result<Unit>
    suspend fun removeMember(teamId: Long, slot: Int)
    suspend fun archiveTeam(teamId: Long)
    suspend fun importFromShowdown(paste: String): Result<Team>
    fun exportToShowdown(team: Team): String
}

interface TypeRepository {
    suspend fun getAllTypes(): List<Type>
    suspend fun getTypeChart(): TypeChart
}

interface TaxonomyRepository {
    fun observeTagTree(): Flow<List<TaxonomyTag>>
    suspend fun getAllTags(): List<TaxonomyTag>
    suspend fun getTagsForPokemon(pokemonId: Int): List<TaxonomyTag>
}

interface SmogonRepository {
    suspend fun getSetsForPokemon(pokemonId: Int, tier: String? = null): List<SmogonSet>
    suspend fun syncSmogonSets(): Result<Unit>
}
