package com.pokemmo.feature.taxonomy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemmo.core.domain.model.TaxonomyTag
import com.pokemmo.core.ui.component.PokemonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxonomyScreen(
    onPokemonClick: (Int) -> Unit,
    viewModel: TaxonomyViewModel = hiltViewModel(),
) {
    val tagTree by viewModel.tagTree.collectAsStateWithLifecycle()
    val selectedIds by viewModel.selectedTagIds.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Taxonomy Browser") },
                actions = {
                    if (selectedIds.isNotEmpty()) {
                        IconButton(onClick = { viewModel.clearSelection() }) {
                            Icon(Icons.Default.Clear, "Clear filters")
                        }
                    }
                },
            )
        },
    ) { padding ->
        Row(Modifier.padding(padding).fillMaxSize()) {
            // Left panel: category tree
            LazyColumn(
                modifier = Modifier.width(160.dp).fillMaxHeight(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(tagTree) { parent ->
                    TaxonomyCategoryItem(
                        tag = parent,
                        selectedIds = selectedIds,
                        onToggle = viewModel::toggleTag,
                    )
                }
            }

            VerticalDivider()

            // Right panel: results grid
            if (selectedIds.isNotEmpty()) {
                val pokemonPager = viewModel.filteredPokemon.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    items(pokemonPager.itemCount) { index ->
                        val pokemon = pokemonPager[index]
                        if (pokemon != null) {
                            PokemonCard(pokemon = pokemon, onClick = { onPokemonClick(pokemon.id) })
                        }
                    }
                }
            } else {
                Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text(
                        "Select a category to browse",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
private fun TaxonomyCategoryItem(
    tag: TaxonomyTag,
    selectedIds: Set<Int>,
    onToggle: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val isSelected = tag.id in selectedIds

    Column {
        FilterChip(
            selected = isSelected,
            onClick = { onToggle(tag.id) },
            label = { Text("${tag.iconEmoji} ${tag.name}") },
            trailingIcon = if (tag.children.isNotEmpty()) {
                {
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.size(20.dp),
                    ) {
                        Icon(
                            if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            "Toggle",
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
            } else null,
            modifier = Modifier.fillMaxWidth(),
        )

        AnimatedVisibility(expanded) {
            Column(Modifier.padding(start = 16.dp)) {
                tag.children.forEach { child ->
                    FilterChip(
                        selected = child.id in selectedIds,
                        onClick = { onToggle(child.id) },
                        label = { Text("${child.iconEmoji} ${child.name}", style = MaterialTheme.typography.bodySmall) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    )
                }
            }
        }
    }
}
