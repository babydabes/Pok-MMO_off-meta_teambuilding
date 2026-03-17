package com.pokemmo.core.network.repository

import com.pokemmo.core.database.entity.PokemonEntity
import com.pokemmo.core.database.entity.MoveEntity
import com.pokemmo.core.domain.model.*

fun PokemonEntity.toDomain(): Pokemon {
    val types = buildList {
        add(Type(0, type1, typeColorHex(type1)))
        if (type2.isNotBlank()) add(Type(0, type2, typeColorHex(type2)))
    }
    val abilities = buildList {
        if (ability1.isNotBlank()) add(Ability(ability1))
        if (ability2.isNotBlank()) add(Ability(ability2))
        if (hiddenAbility.isNotBlank()) add(Ability(hiddenAbility, "Hidden Ability"))
    }
    return Pokemon(
        id = id, name = name, displayName = displayName,
        generation = generation,
        stats = BaseStats(baseHp, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpeed),
        types = types, abilities = abilities,
        spriteUrl = spriteUrl, spriteShinyUrl = spriteShinyUrl,
        tier = PokemmoTier.fromString(pokemmoTier),
        isLegendary = isLegendary, isMythical = isMythical,
        height = height, weight = weight, flavorText = flavorText,
    )
}

fun MoveEntity.toDomain(type: Type): Move = Move(
    id = id, name = name, displayName = displayName,
    type = type,
    category = when (category) {
        "Physical" -> MoveCategory.Physical
        "Special"  -> MoveCategory.Special
        else       -> MoveCategory.Status
    },
    power = power, accuracy = accuracy, pp = pp,
    priority = priority, description = description,
)

private fun typeColorHex(typeName: String): String = when (typeName) {
    "Normal"   -> "#A8A878"; "Fire"     -> "#F08030"; "Water"    -> "#6890F0"
    "Electric" -> "#F8D030"; "Grass"    -> "#78C850"; "Ice"      -> "#98D8D8"
    "Fighting" -> "#C03028"; "Poison"   -> "#A040A0"; "Ground"   -> "#E0C068"
    "Flying"   -> "#A890F0"; "Psychic"  -> "#F85888"; "Bug"      -> "#A8B820"
    "Rock"     -> "#B8A038"; "Ghost"    -> "#705898"; "Dragon"   -> "#7038F8"
    "Dark"     -> "#705848"; "Steel"    -> "#B8B8D0"; "Fairy"    -> "#EE99AC"
    else       -> "#A8A878"
}
