package com.pokemmo.feature.damagecalc

import androidx.lifecycle.ViewModel
import com.pokemmo.core.domain.model.MoveCategory
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.feature.damagecalc.engine.DamageParams
import com.pokemmo.feature.damagecalc.engine.DamageResult
import com.pokemmo.feature.damagecalc.engine.Gen5DamageFormula
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DamageCalcViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DamageCalcUiState())
    val state: StateFlow<DamageCalcUiState> = _state

    fun onMovePowerChanged(power: Int) {
        _state.update { it.copy(movePower = power) }
        recalculate()
    }

    fun onAttackStatChanged(stat: Int) {
        _state.update { it.copy(attackStat = stat) }
        recalculate()
    }

    fun onDefenseStatChanged(stat: Int) {
        _state.update { it.copy(defenseStat = stat) }
        recalculate()
    }

    fun onStabToggled() {
        _state.update { it.copy(isStab = !it.isStab) }
        recalculate()
    }

    fun onTypeEffectivenessChanged(mult: Double) {
        _state.update { it.copy(typeEffectiveness = mult) }
        recalculate()
    }

    fun onTargetHpChanged(hp: Int) {
        _state.update { it.copy(targetHp = hp) }
        recalculate()
    }

    fun onCritToggled() {
        _state.update { it.copy(isCrit = !it.isCrit) }
        recalculate()
    }

    fun onBurnToggled() {
        _state.update { it.copy(isBurned = !it.isBurned) }
        recalculate()
    }

    fun onWeatherChanged(modifier: Double) {
        _state.update { it.copy(weatherModifier = modifier) }
        recalculate()
    }

    private fun recalculate() {
        val s = _state.value
        if (s.movePower <= 0 || s.attackStat <= 0 || s.defenseStat <= 0) {
            _state.update { it.copy(result = null) }
            return
        }
        val result = Gen5DamageFormula.calculate(
            DamageParams(
                movePower = s.movePower,
                effectiveAtk = s.attackStat,
                effectiveDef = s.defenseStat,
                stabModifier = if (s.isStab) 1.5 else 1.0,
                typeEffectiveness = s.typeEffectiveness,
                critModifier = if (s.isCrit) 2.0 else 1.0,
                burnModifier = if (s.isBurned) 0.5 else 1.0,
                weatherModifier = s.weatherModifier,
            )
        )
        _state.update { it.copy(result = result) }
    }
}

data class DamageCalcUiState(
    val movePower: Int = 0,
    val attackStat: Int = 0,
    val defenseStat: Int = 0,
    val targetHp: Int = 300,
    val isStab: Boolean = false,
    val isCrit: Boolean = false,
    val isBurned: Boolean = false,
    val typeEffectiveness: Double = 1.0,
    val weatherModifier: Double = 1.0,
    val result: DamageResult? = null,
)
