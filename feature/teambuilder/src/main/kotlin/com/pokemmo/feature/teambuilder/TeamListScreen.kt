package com.pokemmo.feature.teambuilder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokemmo.core.domain.model.Team
import com.pokemmo.core.ui.component.PokemonSprite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamListScreen(
    onTeamClick: (Long) -> Unit,
    onNewTeam: (Long) -> Unit,
    viewModel: TeamListViewModel = hiltViewModel(),
) {
    val teams by viewModel.teams.collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Teams") },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.createNewTeam { id -> onNewTeam(id) } },
                icon = { Icon(Icons.Default.Add, "New Team") },
                text = { Text("New Team") },
            )
        },
    ) { padding ->
        if (teams.isEmpty()) {
            Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "No teams yet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Tap + to build your first off-meta team!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(padding),
            ) {
                items(teams, key = { it.id }) { team ->
                    TeamRow(team = team, onClick = { onTeamClick(team.id) })
                }
            }
        }
    }
}

@Composable
private fun TeamRow(team: Team, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                )
                AssistChip(
                    onClick = {},
                    label = { Text(team.format) },
                    modifier = Modifier.height(24.dp),
                )
            }
            Spacer(Modifier.height(8.dp))
            // Show team member sprites in a row
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                team.members.filterNotNull().forEach { member ->
                    PokemonSprite(
                        url = member.pokemon.spriteUrl,
                        contentDescription = member.pokemon.displayName,
                        size = 40.dp,
                    )
                }
                // Empty slots shown as dim placeholders
                repeat(6 - team.filledSlots) {
                    Box(Modifier.size(40.dp))
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "${team.filledSlots}/6 Pokémon",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
