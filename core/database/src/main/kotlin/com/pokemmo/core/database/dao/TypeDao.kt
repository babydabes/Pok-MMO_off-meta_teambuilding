package com.pokemmo.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pokemmo.core.database.entity.TypeEffectivenessEntity
import com.pokemmo.core.database.entity.TypeEntity

@Dao
interface TypeDao {

    @Query("SELECT * FROM type ORDER BY id ASC")
    suspend fun getAllTypes(): List<TypeEntity>

    @Query("SELECT * FROM type WHERE name = :name")
    suspend fun getByName(name: String): TypeEntity?

    @Query("""
        SELECT * FROM type_effectiveness
        WHERE attackingTypeId = :attackingId AND defendingTypeId = :defendingId
    """)
    suspend fun getEffectiveness(attackingId: Int, defendingId: Int): TypeEffectivenessEntity?

    @Query("SELECT * FROM type_effectiveness WHERE attackingTypeId = :attackingId")
    suspend fun getAttackingEffectiveness(attackingId: Int): List<TypeEffectivenessEntity>

    @Query("SELECT * FROM type_effectiveness WHERE defendingTypeId = :defendingId")
    suspend fun getDefendingEffectiveness(defendingId: Int): List<TypeEffectivenessEntity>

    @Query("SELECT * FROM type_effectiveness")
    suspend fun getAllEffectiveness(): List<TypeEffectivenessEntity>

    @Upsert
    suspend fun upsertTypes(types: List<TypeEntity>)

    @Upsert
    suspend fun upsertEffectiveness(entries: List<TypeEffectivenessEntity>)
}
