package com.pokemmo.core.database.seed

import com.pokemmo.core.database.entity.TypeEffectivenessEntity
import com.pokemmo.core.database.entity.TypeEntity

object SeedTypes {

    val types: List<TypeEntity> = listOf(
        TypeEntity(1,  "Normal",   "#A8A878"),
        TypeEntity(2,  "Fire",     "#F08030"),
        TypeEntity(3,  "Water",    "#6890F0"),
        TypeEntity(4,  "Electric", "#F8D030"),
        TypeEntity(5,  "Grass",    "#78C850"),
        TypeEntity(6,  "Ice",      "#98D8D8"),
        TypeEntity(7,  "Fighting", "#C03028"),
        TypeEntity(8,  "Poison",   "#A040A0"),
        TypeEntity(9,  "Ground",   "#E0C068"),
        TypeEntity(10, "Flying",   "#A890F0"),
        TypeEntity(11, "Psychic",  "#F85888"),
        TypeEntity(12, "Bug",      "#A8B820"),
        TypeEntity(13, "Rock",     "#B8A038"),
        TypeEntity(14, "Ghost",    "#705898"),
        TypeEntity(15, "Dragon",   "#7038F8"),
        TypeEntity(16, "Dark",     "#705848"),
        TypeEntity(17, "Steel",    "#B8B8D0"),
        TypeEntity(18, "Fairy",    "#EE99AC"),  // not in Gen 5 but kept for completeness
    )

    // Gen 5 type chart — (attackingId, defendingId) -> multiplier
    // Only non-1.0 entries stored; 1.0 is the default
    val effectiveness: List<TypeEffectivenessEntity> = buildList {
        fun e(atk: Int, def: Int, mult: Float) = add(TypeEffectivenessEntity(atk, def, mult))
        // Normal
        e(1,13,0.5f); e(1,14,0f); e(1,17,0.5f)
        // Fire
        e(2,2,0.5f); e(2,3,0.5f); e(2,5,2f); e(2,6,2f); e(2,12,2f); e(2,13,0.5f); e(2,15,0.5f); e(2,17,2f)
        // Water
        e(3,2,2f); e(3,3,0.5f); e(3,5,0.5f); e(3,9,2f); e(3,13,2f); e(3,15,0.5f)
        // Electric
        e(4,3,2f); e(4,4,0.5f); e(4,5,0.5f); e(4,9,0f); e(4,10,2f); e(4,15,0.5f)
        // Grass
        e(5,2,0.5f); e(5,3,2f); e(5,4,0.5f); e(5,5,0.5f); e(5,8,0.5f); e(5,9,2f); e(5,10,0.5f); e(5,12,0.5f); e(5,13,2f); e(5,15,0.5f); e(5,17,0.5f)
        // Ice
        e(6,2,0.5f); e(6,3,0.5f); e(6,5,2f); e(6,6,0.5f); e(6,9,2f); e(6,10,2f); e(6,15,2f); e(6,17,0.5f)
        // Fighting
        e(7,1,2f); e(7,6,2f); e(7,8,0.5f); e(7,10,0.5f); e(7,11,0.5f); e(7,12,0.5f); e(7,13,2f); e(7,14,0f); e(7,16,2f); e(7,17,2f)
        // Poison
        e(8,5,2f); e(8,8,0.5f); e(8,9,0.5f); e(8,11,2f); e(8,13,0.5f); e(8,14,0.5f); e(8,17,0f)
        // Ground
        e(9,2,2f); e(9,4,2f); e(9,5,0.5f); e(9,8,2f); e(9,9,2f); e(9,10,0f); e(9,13,2f); e(9,17,2f)
        // Flying
        e(10,4,0.5f); e(10,5,2f); e(10,7,2f); e(10,9,0f); e(10,12,2f); e(10,13,0.5f); e(10,17,0.5f)
        // Psychic
        e(11,7,2f); e(11,8,2f); e(11,11,0.5f); e(11,14,0f); e(11,16,0f); e(11,17,0.5f)
        // Bug
        e(12,2,0.5f); e(12,5,2f); e(12,7,0.5f); e(12,8,0.5f); e(12,10,0.5f); e(12,11,2f); e(12,14,2f); e(12,16,2f); e(12,17,0.5f)
        // Rock
        e(13,2,2f); e(13,6,2f); e(13,7,0.5f); e(13,9,0.5f); e(13,10,2f); e(13,12,2f); e(13,17,0.5f)
        // Ghost
        e(14,1,0f); e(14,11,2f); e(14,14,2f); e(14,16,0.5f)
        // Dragon
        e(15,15,2f); e(15,17,0.5f)
        // Dark
        e(16,7,0.5f); e(16,11,2f); e(16,14,2f); e(16,16,0.5f)
        // Steel
        e(17,6,2f); e(17,13,2f); e(17,17,0.5f); e(17,2,0.5f); e(17,3,0.5f); e(17,4,0.5f); e(17,5,0.5f); e(17,9,0.5f); e(17,10,0.5f); e(17,11,0.5f); e(17,12,0.5f); e(17,15,0.5f); e(17,16,0.5f)
        // Poison → Steel = 0 in Gen 1 but let's not add that mistake
    }
}
