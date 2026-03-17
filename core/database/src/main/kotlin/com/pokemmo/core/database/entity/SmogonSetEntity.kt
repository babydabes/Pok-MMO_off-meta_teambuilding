package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smogon_set")
data class SmogonSetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pokemonId: Int,
    val setName: String,
    val tier: String,
    val nature: String,
    val abilityName: String,
    val itemName: String,
    val move1: String,
    val move2: String,
    val move3: String,
    val move4: String,
    val evHp: Int = 0,
    val evAtk: Int = 0,
    val evDef: Int = 0,
    val evSpAtk: Int = 0,
    val evSpDef: Int = 0,
    val evSpeed: Int = 0,
    val description: String = "",
    val usageRank: Int = 0,
    val lastSynced: Long = 0L
)
