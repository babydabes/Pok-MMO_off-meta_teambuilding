package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "pokemon_fts")
@Fts4(contentEntity = PokemonEntity::class)
data class PokemonFtsEntity(
    val name: String,
    val displayName: String,
    val type1: String,
    val type2: String,
    val flavorText: String
)
