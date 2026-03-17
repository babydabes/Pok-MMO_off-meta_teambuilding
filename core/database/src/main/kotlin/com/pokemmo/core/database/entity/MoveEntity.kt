package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "move")
data class MoveEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val displayName: String,
    val typeName: String,
    val category: String,       // "Physical", "Special", "Status"
    val power: Int,             // 0 = N/A
    val accuracy: Int,          // 0 = always hits
    val pp: Int,
    val priority: Int,
    val description: String,
    val generation: Int
)

@Entity(
    tableName = "move_learnset",
    primaryKeys = ["pokemonId", "moveId"]
)
data class MoveLearnsetEntity(
    val pokemonId: Int,
    val moveId: Int,
    val learnMethod: String     // "level-up", "tm", "egg", "tutor"
)
