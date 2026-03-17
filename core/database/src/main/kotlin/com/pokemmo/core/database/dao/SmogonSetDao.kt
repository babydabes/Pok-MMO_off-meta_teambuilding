package com.pokemmo.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pokemmo.core.database.entity.SmogonSetEntity

@Dao
interface SmogonSetDao {

    @Query("SELECT * FROM smogon_set WHERE pokemonId = :pokemonId ORDER BY usageRank ASC")
    suspend fun getSetsForPokemon(pokemonId: Int): List<SmogonSetEntity>

    @Query("SELECT * FROM smogon_set WHERE pokemonId = :pokemonId AND tier = :tier ORDER BY usageRank ASC")
    suspend fun getSetsForPokemonByTier(pokemonId: Int, tier: String): List<SmogonSetEntity>

    @Query("SELECT * FROM smogon_set WHERE pokemonId = :pokemonId ORDER BY usageRank ASC LIMIT 1")
    suspend fun getBestSet(pokemonId: Int): SmogonSetEntity?

    @Query("SELECT * FROM smogon_set WHERE pokemonId = :pokemonId AND tier = :tier ORDER BY usageRank ASC LIMIT 1")
    suspend fun getBestSetByTier(pokemonId: Int, tier: String): SmogonSetEntity?

    @Upsert
    suspend fun upsertAll(sets: List<SmogonSetEntity>)

    @Query("DELETE FROM smogon_set WHERE pokemonId = :pokemonId")
    suspend fun deleteSetsForPokemon(pokemonId: Int)
}
