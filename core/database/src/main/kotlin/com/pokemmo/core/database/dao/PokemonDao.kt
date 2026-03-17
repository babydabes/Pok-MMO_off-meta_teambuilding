package com.pokemmo.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pokemmo.core.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getAllPaged(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE pokemmoTier = :tier ORDER BY bst DESC")
    fun getByTierPaged(tier: String): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE generation <= 5 ORDER BY id ASC")
    fun getGen5Paged(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getById(id: Int): PokemonEntity?

    @Query("SELECT * FROM pokemon WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<PokemonEntity>

    // FTS full-text search — joins FTS virtual table to pokemon for full entity data
    @Query("""
        SELECT p.* FROM pokemon p
        INNER JOIN pokemon_fts ON p.rowid = pokemon_fts.rowid
        WHERE pokemon_fts MATCH :query
        ORDER BY p.bst DESC
        LIMIT 60
    """)
    suspend fun searchFts(query: String): List<PokemonEntity>

    @Query("""
        SELECT p.* FROM pokemon p
        INNER JOIN pokemon_fts ON p.rowid = pokemon_fts.rowid
        WHERE pokemon_fts MATCH :query
        ORDER BY p.bst DESC
    """)
    fun searchFtsPaged(query: String): PagingSource<Int, PokemonEntity>

    @Query("""
        SELECT p.* FROM pokemon p
        INNER JOIN pokemon_fts ON p.rowid = pokemon_fts.rowid
        WHERE pokemon_fts MATCH :query
          AND (p.type1 = :type OR p.type2 = :type)
        ORDER BY p.bst DESC
    """)
    fun searchFtsFilteredByType(query: String, type: String): PagingSource<Int, PokemonEntity>

    @Query("""
        SELECT p.* FROM pokemon p
        INNER JOIN pokemon_taxonomy pt ON p.id = pt.pokemonId
        WHERE pt.taxonomyTagId IN (:tagIds)
        GROUP BY p.id
        ORDER BY p.bst DESC
    """)
    fun getByTaxonomyTagsPaged(tagIds: List<Int>): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE type1 = :type OR type2 = :type ORDER BY bst DESC")
    fun getByTypePaged(type: String): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemon ORDER BY name ASC")
    fun observeAll(): Flow<List<PokemonEntity>>

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun count(): Int

    @Upsert
    suspend fun upsertAll(pokemon: List<PokemonEntity>)
}
