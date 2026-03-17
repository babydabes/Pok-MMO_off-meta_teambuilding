package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val displayName: String,
    val generation: Int,
    val baseHp: Int,
    val baseAtk: Int,
    val baseDef: Int,
    val baseSpAtk: Int,
    val baseSpDef: Int,
    val baseSpeed: Int,
    val bst: Int,
    val type1: String,
    val type2: String,              // empty string means single-type
    val ability1: String,
    val ability2: String,           // empty string if only one
    val hiddenAbility: String,      // empty string if none
    val spriteUrl: String,
    val spriteShinyUrl: String,
    val pokemmoTier: String,        // "OU","UU","NU","Untiered","Uber"
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val height: Float,
    val weight: Float,
    val flavorText: String,
    val lastSynced: Long = 0L
)
