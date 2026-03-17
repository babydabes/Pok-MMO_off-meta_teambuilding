package com.pokemmo.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pokemmo.core.database.entity.PokemonTaxonomyCrossRef
import com.pokemmo.core.database.entity.TaxonomyTagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaxonomyDao {

    @Query("SELECT * FROM taxonomy_tag ORDER BY sortOrder ASC")
    fun observeAllTags(): Flow<List<TaxonomyTagEntity>>

    @Query("SELECT * FROM taxonomy_tag ORDER BY sortOrder ASC")
    suspend fun getAllTags(): List<TaxonomyTagEntity>

    @Query("SELECT * FROM taxonomy_tag WHERE parentId = 0 ORDER BY sortOrder ASC")
    suspend fun getTopLevelCategories(): List<TaxonomyTagEntity>

    @Query("SELECT * FROM taxonomy_tag WHERE parentId = :parentId ORDER BY sortOrder ASC")
    suspend fun getSubcategories(parentId: Int): List<TaxonomyTagEntity>

    @Query("""
        SELECT t.* FROM taxonomy_tag t
        INNER JOIN pokemon_taxonomy pt ON t.id = pt.taxonomyTagId
        WHERE pt.pokemonId = :pokemonId
    """)
    suspend fun getTagsForPokemon(pokemonId: Int): List<TaxonomyTagEntity>

    @Upsert
    suspend fun upsertTags(tags: List<TaxonomyTagEntity>)

    @Upsert
    suspend fun upsertCrossRefs(refs: List<PokemonTaxonomyCrossRef>)

    @Query("SELECT COUNT(*) FROM taxonomy_tag")
    suspend fun count(): Int
}
