package com.pokemmo.core.domain.model

// ---------------------------------------------------------------------------
// Type
// ---------------------------------------------------------------------------

data class Type(
    val id: Int,
    val name: String,
    val colorHex: String,
)

// ---------------------------------------------------------------------------
// Ability
// ---------------------------------------------------------------------------

data class Ability(
    val name: String,
    val description: String = "",
)

// ---------------------------------------------------------------------------
// Move
// ---------------------------------------------------------------------------

enum class MoveCategory { Physical, Special, Status }

data class Move(
    val id: Int,
    val name: String,
    val displayName: String,
    val type: Type,
    val category: MoveCategory,
    val power: Int,
    val accuracy: Int,
    val pp: Int,
    val priority: Int,
    val description: String,
)

// ---------------------------------------------------------------------------
// Pokémon
// ---------------------------------------------------------------------------

enum class PokemmoTier(val label: String) {
    OU("OU"), UU("UU"), NU("NU"), UBER("Uber"), UNTIERED("Untiered");

    companion object {
        fun fromString(s: String): PokemmoTier? = entries.firstOrNull { it.label == s }
    }
}

data class BaseStats(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
) {
    val bst: Int get() = hp + atk + def + spAtk + spDef + speed
}

data class Pokemon(
    val id: Int,
    val name: String,
    val displayName: String,
    val generation: Int,
    val stats: BaseStats,
    val types: List<Type>,
    val abilities: List<Ability>,
    val spriteUrl: String,
    val spriteShinyUrl: String,
    val tier: PokemmoTier?,
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val height: Float,
    val weight: Float,
    val flavorText: String,
    val taxonomyTags: List<TaxonomyTag> = emptyList(),
)

// ---------------------------------------------------------------------------
// Team
// ---------------------------------------------------------------------------

enum class Nature(val atk: Float, val def: Float, val spAtk: Float, val spDef: Float, val speed: Float) {
    Hardy(1f, 1f, 1f, 1f, 1f),
    Lonely(1.1f, 0.9f, 1f, 1f, 1f),
    Brave(1.1f, 1f, 1f, 1f, 0.9f),
    Adamant(1.1f, 1f, 0.9f, 1f, 1f),
    Naughty(1.1f, 1f, 1f, 0.9f, 1f),
    Bold(0.9f, 1.1f, 1f, 1f, 1f),
    Docile(1f, 1f, 1f, 1f, 1f),
    Relaxed(0.9f, 1.1f, 1f, 1f, 0.9f),
    Impish(0.9f, 1.1f, 0.9f, 1f, 1f),
    Lax(0.9f, 1.1f, 1f, 0.9f, 1f),
    Timid(0.9f, 1f, 1f, 1f, 1.1f),
    Hasty(0.9f, 0.9f, 1f, 1f, 1.1f),
    Serious(1f, 1f, 1f, 1f, 1f),
    Jolly(0.9f, 1f, 0.9f, 1f, 1.1f),
    Naive(0.9f, 1f, 1f, 0.9f, 1.1f),
    Modest(0.9f, 1f, 1.1f, 1f, 1f),
    Mild(0.9f, 0.9f, 1.1f, 1f, 1f),
    Quiet(0.9f, 1f, 1.1f, 1f, 0.9f),
    Bashful(1f, 1f, 1f, 1f, 1f),
    Rash(0.9f, 1f, 1.1f, 0.9f, 1f),
    Calm(0.9f, 1f, 1f, 1.1f, 1f),
    Gentle(0.9f, 0.9f, 1f, 1.1f, 1f),
    Sassy(0.9f, 1f, 1f, 1.1f, 0.9f),
    Careful(0.9f, 1f, 0.9f, 1.1f, 1f),
    Quirky(1f, 1f, 1f, 1f, 1f);

    companion object {
        fun fromName(name: String): Nature = entries.firstOrNull {
            it.name.equals(name, ignoreCase = true)
        } ?: Hardy
    }
}

data class StatSpread(
    val hp: Int = 0,
    val atk: Int = 0,
    val def: Int = 0,
    val spAtk: Int = 0,
    val spDef: Int = 0,
    val speed: Int = 0,
) {
    val total: Int get() = hp + atk + def + spAtk + spDef + speed

    fun toShowdownString(): String = buildList {
        if (hp > 0) add("$hp HP")
        if (atk > 0) add("$atk Atk")
        if (def > 0) add("$def Def")
        if (spAtk > 0) add("$spAtk SpA")
        if (spDef > 0) add("$spDef SpD")
        if (speed > 0) add("$speed Spe")
    }.joinToString(" / ")
}

data class TeamMember(
    val slot: Int,
    val pokemon: Pokemon,
    val nickname: String,
    val level: Int = 100,
    val item: String,
    val ability: Ability,
    val nature: Nature,
    val moves: List<Move?>,         // always 4 elements, null = empty slot
    val evs: StatSpread,
    val ivs: StatSpread,
    val isShiny: Boolean = false,
) {
    val displayName: String get() = nickname.ifBlank { pokemon.displayName }
}

data class Team(
    val id: Long,
    val name: String,
    val format: String,
    val members: List<TeamMember?>,  // 6 slots, null = empty
    val notes: String,
    val createdAt: Long,
    val updatedAt: Long,
) {
    val filledSlots: Int get() = members.count { it != null }
    val isEmpty: Boolean get() = filledSlots == 0
}

// ---------------------------------------------------------------------------
// Taxonomy
// ---------------------------------------------------------------------------

data class TaxonomyTag(
    val id: Int,
    val name: String,
    val parentId: Int,   // 0 = top-level
    val iconEmoji: String,
    val sortOrder: Int,
    val children: List<TaxonomyTag> = emptyList(),
)

// ---------------------------------------------------------------------------
// Smogon Set
// ---------------------------------------------------------------------------

data class SmogonSet(
    val id: Long,
    val pokemonId: Int,
    val setName: String,
    val tier: String,
    val nature: Nature,
    val ability: String,
    val item: String,
    val moves: List<String>,       // 4 move names
    val evs: StatSpread,
    val description: String,
    val usageRank: Int,
)

// ---------------------------------------------------------------------------
// Coverage analysis
// ---------------------------------------------------------------------------

data class CoverageResult(
    /** For each type, the maximum offensive multiplier any team move achieves. */
    val offensiveMultipliers: Map<String, Float>,
    /** For each type, how many team members are weak to it (>1× damage). */
    val defensiveWeaknesses: Map<String, Int>,
    /** For each type, how many team members resist it. */
    val resistances: Map<String, Int>,
    /** For each type, how many team members are immune. */
    val immunities: Map<String, Int>,
) {
    /** Types where no team move hits for super-effective. */
    val uncoveredTypes: List<String>
        get() = offensiveMultipliers.filter { it.value < 2f }.keys.toList()
}

// ---------------------------------------------------------------------------
// TypeChart
// ---------------------------------------------------------------------------

data class TypeChart(
    val types: List<Type>,
    /** (attackingTypeId to defendingTypeId) → multiplier */
    val effectiveness: Map<Pair<Int, Int>, Float>,
) {
    fun multiplier(attacker: Type, defender: Type): Float =
        effectiveness[attacker.id to defender.id] ?: 1f

    fun combinedDefense(defenderTypes: List<Type>, attacker: Type): Float =
        defenderTypes.fold(1f) { acc, t -> acc * multiplier(attacker, t) }
}
