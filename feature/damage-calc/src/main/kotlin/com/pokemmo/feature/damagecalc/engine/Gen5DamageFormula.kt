package com.pokemmo.feature.damagecalc.engine

/**
 * Implements the exact Gen V damage formula.
 *
 * Damage = (((2×Level/5+2) × Power × A/D) / 50 + 2)
 *          × Targets × Weather × Critical × random × STAB × Type1 × Type2 × Burn × Other
 *
 * Random factor is 85..100 in steps of 1 (÷100), producing 16 possible damage rolls.
 */
object Gen5DamageFormula {

    fun calculate(params: DamageParams): DamageResult {
        val base = ((2.0 * params.level / 5.0 + 2.0) *
            params.movePower * params.effectiveAtk.toDouble() / params.effectiveDef) / 50.0 + 2.0

        val modified = base *
            params.targetsModifier *
            params.weatherModifier *
            params.critModifier *
            params.stabModifier *
            params.typeEffectiveness *
            params.burnModifier *
            params.otherModifier

        val minDmg = (modified * 0.85).toInt().coerceAtLeast(1)
        val maxDmg = modified.toInt().coerceAtLeast(1)

        return DamageResult(
            min = minDmg,
            max = maxDmg,
            rolls = (85..100).map { r -> (modified * r / 100.0).toInt().coerceAtLeast(1) },
        )
    }

    /** Calculate effective stat after EV/IV/Nature at a given level. */
    fun effectiveStat(
        base: Int,
        iv: Int = 31,
        ev: Int = 0,
        level: Int = 100,
        natureMod: Float = 1f,
        isHp: Boolean = false,
    ): Int {
        val inner = (2 * base + iv + ev / 4) * level / 100
        return if (isHp) {
            inner + level + 10
        } else {
            ((inner + 5) * natureMod).toInt()
        }
    }
}

data class DamageParams(
    val level: Int = 100,
    val movePower: Int,
    val effectiveAtk: Int,
    val effectiveDef: Int,
    val targetsModifier: Double = 1.0,  // 0.75 for spread moves
    val weatherModifier: Double = 1.0,  // 1.5 for boosted, 0.5 for weakened
    val critModifier: Double = 1.0,     // 2.0 in Gen V
    val stabModifier: Double = 1.0,     // 1.5 if STAB
    val typeEffectiveness: Double = 1.0,
    val burnModifier: Double = 1.0,     // 0.5 if burned + physical
    val otherModifier: Double = 1.0,
)

data class DamageResult(
    val min: Int,
    val max: Int,
    val rolls: List<Int>,
) {
    fun asPercentOf(hp: Int): Pair<Float, Float> =
        (min * 100f / hp) to (max * 100f / hp)
}
