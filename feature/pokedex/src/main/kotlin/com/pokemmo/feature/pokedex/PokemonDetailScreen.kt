package com.pokemmo.feature.pokedex

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.core.ui.component.PokemonSprite
import com.pokemmo.core.ui.component.StatBar
import com.pokemmo.core.ui.component.TypeBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    onNavigateUp: () -> Unit,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemon?.displayName ?: "Loading...") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
            )
        },
    ) { padding ->
        pokemon?.let { pkmn ->
            PokemonDetailContent(
                pokemon = pkmn,
                modifier = Modifier.padding(padding),
            )
        } ?: Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun PokemonDetailContent(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Sprite
        PokemonSprite(
            url = pokemon.spriteUrl,
            contentDescription = pokemon.displayName,
            size = 120.dp,
        )

        Text(
            "#${pokemon.id.toString().padStart(3, '0')} ${pokemon.displayName}",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(Modifier.height(4.dp))

        // Types
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pokemon.types.forEach { TypeBadge(it.name) }
        }

        Spacer(Modifier.height(4.dp))

        // Tier + meta info
        pokemon.tier?.let {
            AssistChip(onClick = {}, label = { Text("Tier: ${it.label}") })
        }
        Text(
            "Gen ${pokemon.generation} | ${pokemon.height}m | ${pokemon.weight}kg",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(16.dp))

        // Flavor text
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Text(
                pokemon.flavorText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp),
            )
        }

        Spacer(Modifier.height(16.dp))

        // Stats
        Text("Base Stats", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        StatBar(label = "HP",    value = pokemon.stats.hp)
        StatBar(label = "Atk",   value = pokemon.stats.atk)
        StatBar(label = "Def",   value = pokemon.stats.def)
        StatBar(label = "SpAtk", value = pokemon.stats.spAtk)
        StatBar(label = "SpDef", value = pokemon.stats.spDef)
        StatBar(label = "Spe",   value = pokemon.stats.speed)
        Spacer(Modifier.height(4.dp))
        Text(
            "BST: ${pokemon.stats.bst}",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(16.dp))

        // Abilities
        Text("Abilities", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(4.dp))
        pokemon.abilities.forEach { ability ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(ability.name, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                if (ability.description.isNotBlank()) {
                    Text(ability.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}
