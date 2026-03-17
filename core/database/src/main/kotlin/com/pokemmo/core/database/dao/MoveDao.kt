package com.pokemmo.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pokemmo.core.database.entity.MoveEntity
import com.pokemmo.core.database.entity.MoveLearnsetEntity

@Dao
interface MoveDao {

    @Query("SELECT * FROM move WHERE id = :id")
    suspend fun getById(id: Int): MoveEntity?

    @Query("SELECT * FROM move WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<MoveEntity>

    @Query("""
        SELECT m.* FROM move m
        INNER JOIN move_learnset ml ON m.id = ml.moveId
        WHERE ml.pokemonId = :pokemonId
        ORDER BY m.displayName ASC
    """)
    suspend fun getMovesForPokemon(pokemonId: Int): List<MoveEntity>

    @Query("""
        SELECT m.* FROM move m
        INNER JOIN move_learnset ml ON m.id = ml.moveId
        WHERE ml.pokemonId = :pokemonId AND ml.learnMethod = :method
        ORDER BY m.displayName ASC
    """)
    suspend fun getMovesByLearnMethod(pokemonId: Int, method: String): List<MoveEntity>

    @Query("SELECT * FROM move WHERE displayName LIKE '%' || :query || '%' AND generation <= 5 LIMIT 30")
    suspend fun searchMoves(query: String): List<MoveEntity>

    @Upsert
    suspend fun upsertAll(moves: List<MoveEntity>)

    @Upsert
    suspend fun upsertLearnsets(learnsets: List<MoveLearnsetEntity>)
}
