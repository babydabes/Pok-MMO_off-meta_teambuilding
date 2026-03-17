package com.pokemmo.feature.pokedex

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemmo.core.domain.model.PokemmoTier
import com.pokemmo.core.ui.component.PokemonCard
import com.pokemmo.core.ui.component.SkeletonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen(
    onPokemonClick: (Int) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pokemonPager = viewModel.pokemonPagingData.collectAsLazyPagingItems()
    var showFilterSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokédex") })
        },
    ) { padding ->
        Column(Modifier.padding(padding)) {
            // Search bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onQueryChanged,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                placeholder = { Text("Search Pokémon…") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    BadgedBox(
                        badge = {
                            if (uiState.filters.activeCount > 0) {
                                Badge { Text(uiState.filters.activeCount.toString()) }
                            }
                        }
                    ) {
                        IconButton(onClick = { showFilterSheet = true }) {
                            Icon(Icons.Default.FilterList, "Filters")
                        }
                    }
                },
                singleLine = true,
            )

            Spacer(Modifier.height(8.dp))

            // Active filter chips
            if (uiState.filters.activeCount > 0) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    uiState.filters.typeFilter?.let { type ->
                        InputChip(
                            selected = true,
                            onClick = { viewModel.onTypeFilterSelected(null) },
                            label = { Text(type) },
                        )
                    }
                    uiState.filters.tierFilter?.let { tier ->
                        InputChip(
                            selected = true,
                            onClick = { viewModel.onTierFilterSelected(null) },
                            label = { Text(tier.label) },
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
            }

            // Paged grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(
                    count = pokemonPager.itemCount,
                    key = { index -> pokemonPager.peek(index)?.id ?: index },
                ) { index ->
                    val pokemon = pokemonPager[index]
                    if (pokemon != null) {
                        PokemonCard(pokemon = pokemon, onClick = { onPokemonClick(pokemon.id) })
                    } else {
                        SkeletonCard()
                    }
                }

                when (pokemonPager.loadState.append) {
                    is LoadState.Loading -> item(span = { GridItemSpan(maxLineSpan) }) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth().wrapContentWidth().padding(16.dp)
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    // Filter bottom sheet
    if (showFilterSheet) {
        ModalBottomSheet(onDismissRequest = { showFilterSheet = false }) {
            FilterSheet(
                currentFilters = uiState.filters,
                onTypeSelected = { viewModel.onTypeFilterSelected(it) },
                onTierSelected = { viewModel.onTierFilterSelected(it) },
                onDismiss = { showFilterSheet = false },
            )
        }
    }
}

@Composable
private fun FilterSheet(
    currentFilters: PokedexFilters,
    onTypeSelected: (String?) -> Unit,
    onTierSelected: (PokemmoTier?) -> Unit,
    onDismiss: () -> Unit,
) {
    val types = listOf(
        "Normal", "Fire", "Water", "Electric", "Grass", "Ice",
        "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug",
        "Rock", "Ghost", "Dragon", "Dark", "Steel",
    )

    Column(Modifier.padding(16.dp)) {
        Text("Filter by Type", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            types.forEach { type ->
                FilterChip(
                    selected = currentFilters.typeFilter == type,
                    onClick = {
                        onTypeSelected(if (currentFilters.typeFilter == type) null else type)
                    },
                    label = { Text(type) },
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Filter by Tier", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            PokemmoTier.values().forEach { tier ->
                FilterChip(
                    selected = currentFilters.tierFilter == tier,
                    onClick = {
                        onTierSelected(if (currentFilters.tierFilter == tier) null else tier)
                    },
                    label = { Text(tier.label) },
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        FilledTonalButton(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth(),
        ) { Text("Done") }

        Spacer(Modifier.height(32.dp))
    }
}
