package com.pokemmo.feature.teambuilder

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemmo.core.domain.model.TeamMember
import com.pokemmo.core.ui.component.PokemonSprite
import com.pokemmo.core.ui.component.TypeBadge
import com.pokemmo.core.ui.component.TypeCoverageStrip

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TeamBuilderScreen(
    teamId: Long,
    onNavigateUp: () -> Unit,
    viewModel: TeamBuilderViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is TeamBuilderEffect.ShowError ->
                    snackbarHostState.showSnackbar(effect.message)
                is TeamBuilderEffect.ShowSnackbar ->
                    snackbarHostState.showSnackbar(effect.message)
                is TeamBuilderEffect.ShareExport -> {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, effect.paste)
                    }
                    context.startActivity(Intent.createChooser(intent, "Export Team"))
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(uiState.team?.name ?: "New Team", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onIntent(TeamBuilderIntent.ExportTeam) }) {
                        Icon(Icons.Default.Share, "Export")
                    }
                    IconButton(onClick = { viewModel.onIntent(TeamBuilderIntent.ToggleImportDialog) }) {
                        Icon(Icons.Default.Download, "Import")
                    }
                },
            )
        },
    ) { padding ->
        Column(Modifier.padding(padding)) {
            if (uiState.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                // 2×3 Team Slot Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    items(6) { slot ->
                        val member = uiState.team?.members?.getOrNull(slot)
                        AnimatedContent(targetState = member, label = "slot_$slot") { m ->
                            if (m != null) {
                                FilledSlotCard(
                                    member = m,
                                    isExpanded = slot == uiState.expandedMemberSlot,
                                    onClick = { viewModel.onIntent(TeamBuilderIntent.ExpandMember(slot)) },
                                    onLongClick = { viewModel.onIntent(TeamBuilderIntent.RemoveMember(slot)) },
                                )
                            } else {
                                EmptySlotCard(
                                    slot = slot,
                                    onClick = { viewModel.onIntent(TeamBuilderIntent.SelectSlot(slot)) },
                                )
                            }
                        }
                    }
                }

                // Smogon set suggestions
                AnimatedVisibility(uiState.suggestedSets.isNotEmpty()) {
                    Column(Modifier.padding(horizontal = 12.dp)) {
                        Text("Suggested Sets", style = MaterialTheme.typography.labelLarge)
                        Spacer(Modifier.height(4.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(uiState.suggestedSets) { set ->
                                SuggestionChip(
                                    onClick = {
                                        viewModel.onIntent(
                                            TeamBuilderIntent.ApplySmogonSet(uiState.activeSuggestionSlot, set)
                                        )
                                    },
                                    label = { Text(set.setName) },
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                }

                // Coverage strip
                uiState.coverage?.let { TypeCoverageStrip(coverage = it) }
            }
        }
    }

    // Search overlay
    if (uiState.isSearchOpen) {
        PokemonSearchSheet(
            query = uiState.searchQuery,
            onQueryChange = { viewModel.onIntent(TeamBuilderIntent.SearchQueryChanged(it)) },
            searchResults = uiState.searchResults,
            onPokemonSelected = { id ->
                viewModel.onIntent(TeamBuilderIntent.AddPokemon(id, uiState.activeSlot))
            },
            onDismiss = { viewModel.onIntent(TeamBuilderIntent.DismissSearch) },
        )
    }

    // Import dialog
    if (uiState.showImportDialog) {
        ImportShowdownDialog(
            onImport = { paste -> viewModel.onIntent(TeamBuilderIntent.ImportPaste(paste)) },
            onDismiss = { viewModel.onIntent(TeamBuilderIntent.ToggleImportDialog) },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FilledSlotCard(
    member: TeamMember,
    isExpanded: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        ),
    ) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            PokemonSprite(
                url = if (member.isShiny) member.pokemon.spriteShinyUrl else member.pokemon.spriteUrl,
                contentDescription = member.displayName,
                size = 56.dp,
            )
            Text(
                member.displayName,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                member.pokemon.types.forEach { TypeBadge(it.name) }
            }

            if (isExpanded) {
                Spacer(Modifier.height(4.dp))
                Text("${member.nature.name} | ${member.ability.name}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (member.item.isNotBlank()) {
                    Text("Item: ${member.item}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    "EVs: ${member.evs.toShowdownString().ifBlank { "None" }}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun EmptySlotCard(slot: Int, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(120.dp),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Pokémon",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    "Slot ${slot + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonSearchSheet(
    query: String,
    onQueryChange: (String) -> Unit,
    searchResults: androidx.paging.PagingData<com.pokemmo.core.domain.model.Pokemon>,
    onPokemonSelected: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val pagingItems = remember(searchResults) {
        kotlinx.coroutines.flow.flowOf(searchResults)
    }.collectAsLazyPagingItems()

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.padding(16.dp).fillMaxHeight(0.8f)) {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search Pokémon...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                singleLine = true,
            )
            Spacer(Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(pagingItems.itemCount) { index ->
                    val pokemon = pagingItems[index]
                    if (pokemon != null) {
                        com.pokemmo.core.ui.component.PokemonCard(
                            pokemon = pokemon,
                            onClick = { onPokemonSelected(pokemon.id) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImportShowdownDialog(
    onImport: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var paste by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Import Showdown Paste") },
        text = {
            OutlinedTextField(
                value = paste,
                onValueChange = { paste = it },
                modifier = Modifier.fillMaxWidth().height(200.dp),
                placeholder = { Text("Paste your Showdown team here...") },
                minLines = 5,
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onImport(paste) },
                enabled = paste.isNotBlank(),
            ) { Text("Import") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
    )
}
