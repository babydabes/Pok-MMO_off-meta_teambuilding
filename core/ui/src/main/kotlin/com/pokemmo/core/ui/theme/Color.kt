package com.pokemmo.core.ui.theme

import androidx.compose.ui.graphics.Color

object PokeMMOColors {
    val DarkBackground   = Color(0xFF0D0D0D)
    val DarkSurface      = Color(0xFF1A1A2E)
    val DarkSurfaceVar   = Color(0xFF16213E)
    val AccentRed        = Color(0xFFE53935)
    val AccentGold       = Color(0xFFFFD740)
    val OnDark           = Color(0xFFEEEEEE)
    val OnDarkDim        = Color(0xFFAAAAAA)

    // Pokémon type colors
    val TypeNormal   = Color(0xFFA8A878)
    val TypeFire     = Color(0xFFF08030)
    val TypeWater    = Color(0xFF6890F0)
    val TypeGrass    = Color(0xFF78C850)
    val TypeElectric = Color(0xFFF8D030)
    val TypeIce      = Color(0xFF98D8D8)
    val TypeFighting = Color(0xFFC03028)
    val TypePoison   = Color(0xFFA040A0)
    val TypeGround   = Color(0xFFE0C068)
    val TypeFlying   = Color(0xFFA890F0)
    val TypePsychic  = Color(0xFFF85888)
    val TypeBug      = Color(0xFFA8B820)
    val TypeRock     = Color(0xFFB8A038)
    val TypeGhost    = Color(0xFF705898)
    val TypeDragon   = Color(0xFF7038F8)
    val TypeDark     = Color(0xFF705848)
    val TypeSteel    = Color(0xFFB8B8D0)
    val TypeFairy    = Color(0xFFEE99AC)
}

fun typeColor(typeName: String): Color = when (typeName) {
    "Normal"   -> PokeMMOColors.TypeNormal
    "Fire"     -> PokeMMOColors.TypeFire
    "Water"    -> PokeMMOColors.TypeWater
    "Grass"    -> PokeMMOColors.TypeGrass
    "Electric" -> PokeMMOColors.TypeElectric
    "Ice"      -> PokeMMOColors.TypeIce
    "Fighting" -> PokeMMOColors.TypeFighting
    "Poison"   -> PokeMMOColors.TypePoison
    "Ground"   -> PokeMMOColors.TypeGround
    "Flying"   -> PokeMMOColors.TypeFlying
    "Psychic"  -> PokeMMOColors.TypePsychic
    "Bug"      -> PokeMMOColors.TypeBug
    "Rock"     -> PokeMMOColors.TypeRock
    "Ghost"    -> PokeMMOColors.TypeGhost
    "Dragon"   -> PokeMMOColors.TypeDragon
    "Dark"     -> PokeMMOColors.TypeDark
    "Steel"    -> PokeMMOColors.TypeSteel
    "Fairy"    -> PokeMMOColors.TypeFairy
    else       -> PokeMMOColors.TypeNormal
}
