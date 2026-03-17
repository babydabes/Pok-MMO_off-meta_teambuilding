package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val colorHex: String        // e.g. "#78C850"
)

@Entity(
    tableName = "type_effectiveness",
    primaryKeys = ["attackingTypeId", "defendingTypeId"]
)
data class TypeEffectivenessEntity(
    val attackingTypeId: Int,
    val defendingTypeId: Int,
    val multiplier: Float       // 0.0, 0.5, 1.0, 2.0
)
