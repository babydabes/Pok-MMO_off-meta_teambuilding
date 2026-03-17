package com.pokemmo.feature.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.core.domain.model.PokemmoTier
import com.pokemmo.core.domain.usecase.GetPagedPokemonUseCase
import com.pokemmo.core.domain.usecase.SearchByTaxonomyUseCase
import com.pokemmo.core.domain.usecase.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPagedPokemonUseCase: GetPagedPokemonUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val searchByTaxonomyUseCase: SearchByTaxonomyUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokedexUiState())
    val uiState: StateFlow<PokedexUiState> = _uiState.asStateFlow()

    private val searchQueryFlow = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pokemonPagingData: Flow<PagingData<Pokemon>> =
        combine(
            searchQueryFlow.debounce(300),
            _uiState.map { it.filters }.distinctUntilChanged(),
        ) { query, filters -> query to filters }
            .flatMapLatest { (query, filters) ->
                when {
                    filters.taxonomyTagIds.isNotEmpty() ->
                        searchByTaxonomyUseCase(filters.taxonomyTagIds.toList())
                    query.length >= 2 ->
                        searchPokemonUseCase(query, filters.typeFilter)
                    filters.tierFilter != null ->
                        getPagedPokemonUseCase(filters.tierFilter)
                    else ->
                        getPagedPokemonUseCase()
                }
            }
            .cachedIn(viewModelScope)

    fun onQueryChanged(query: String) {
        searchQueryFlow.value = query
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onTypeFilterSelected(type: String?) {
        _uiState.update { it.copy(filters = it.filters.copy(typeFilter = type)) }
    }

    fun onTierFilterSelected(tier: PokemmoTier?) {
        _uiState.update { it.copy(filters = it.filters.copy(tierFilter = tier)) }
    }

    fun onTaxonomyTagToggled(tagId: Int) {
        _uiState.update { state ->
            val current = state.filters.taxonomyTagIds
            val updated = if (tagId in current) current - tagId else current + tagId
            state.copy(filters = state.filters.copy(taxonomyTagIds = updated))
        }
    }
}

data class PokedexUiState(
    val searchQuery: String = "",
    val filters: PokedexFilters = PokedexFilters(),
)

data class PokedexFilters(
    val typeFilter: String? = null,
    val tierFilter: PokemmoTier? = null,
    val taxonomyTagIds: Set<Int> = emptySet(),
) {
    val activeCount: Int get() = listOfNotNull(typeFilter, tierFilter).size + taxonomyTagIds.size
}
