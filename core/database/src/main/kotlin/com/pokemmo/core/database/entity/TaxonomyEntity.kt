package com.pokemmo.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "taxonomy_tag")
data class TaxonomyTagEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val parentId: Int,          // 0 = top-level
    val iconEmoji: String,
    val sortOrder: Int
)

@Entity(
    tableName = "pokemon_taxonomy",
    primaryKeys = ["pokemonId", "taxonomyTagId"],
    indices = [Index("taxonomyTagId")]
)
data class PokemonTaxonomyCrossRef(
    val pokemonId: Int,
    val taxonomyTagId: Int
)
