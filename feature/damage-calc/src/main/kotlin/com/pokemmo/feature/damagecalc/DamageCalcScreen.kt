package com.pokemmo.feature.damagecalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DamageCalcScreen(
    viewModel: DamageCalcViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Gen V Damage Calc") }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Attacker", style = MaterialTheme.typography.titleMedium)

            NumericField(
                label = "Move Power",
                value = state.movePower,
                onValueChange = viewModel::onMovePowerChanged,
            )
            NumericField(
                label = "Effective Atk/SpAtk",
                value = state.attackStat,
                onValueChange = viewModel::onAttackStatChanged,
            )

            Divider()

            Text("Defender", style = MaterialTheme.typography.titleMedium)

            NumericField(
                label = "Effective Def/SpDef",
                value = state.defenseStat,
                onValueChange = viewModel::onDefenseStatChanged,
            )
            NumericField(
                label = "Target HP",
                value = state.targetHp,
                onValueChange = viewModel::onTargetHpChanged,
            )

            Divider()

            Text("Modifiers", style = MaterialTheme.typography.titleMedium)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = state.isStab,
                    onClick = viewModel::onStabToggled,
                    label = { Text("STAB") },
                )
                FilterChip(
                    selected = state.isCrit,
                    onClick = viewModel::onCritToggled,
                    label = { Text("Crit") },
                )
                FilterChip(
                    selected = state.isBurned,
                    onClick = viewModel::onBurnToggled,
                    label = { Text("Burn") },
                )
            }

            // Type effectiveness selector
            Text("Type Effectiveness", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf(0.25, 0.5, 1.0, 2.0, 4.0).forEach { mult ->
                    FilterChip(
                        selected = state.typeEffectiveness == mult,
                        onClick = { viewModel.onTypeEffectivenessChanged(mult) },
                        label = { Text("×$mult") },
                    )
                }
            }

            // Weather modifier
            Text("Weather", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf("None" to 1.0, "Boost" to 1.5, "Weaken" to 0.5).forEach { (label, mod) ->
                    FilterChip(
                        selected = state.weatherModifier == mod,
                        onClick = { viewModel.onWeatherChanged(mod) },
                        label = { Text(label) },
                    )
                }
            }

            Divider()

            // Result
            state.result?.let { result ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Damage Result", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "${result.min} – ${result.max} damage",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        val (minPct, maxPct) = result.asPercentOf(state.targetHp)
                        Text(
                            "%.1f%% – %.1f%% of target HP".format(minPct, maxPct),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Spacer(Modifier.height(4.dp))
                        val hitsToKo = if (result.min > 0) {
                            val minHits = (state.targetHp + result.max - 1) / result.max
                            val maxHits = (state.targetHp + result.min - 1) / result.min
                            if (minHits == maxHits) "${minHits}HKO" else "${minHits}–${maxHits}HKO"
                        } else "∞"
                        Text(
                            "Guaranteed: $hitsToKo",
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NumericField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
) {
    OutlinedTextField(
        value = if (value == 0) "" else value.toString(),
        onValueChange = { text ->
            onValueChange(text.toIntOrNull()?.coerceIn(0, 9999) ?: 0)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}
