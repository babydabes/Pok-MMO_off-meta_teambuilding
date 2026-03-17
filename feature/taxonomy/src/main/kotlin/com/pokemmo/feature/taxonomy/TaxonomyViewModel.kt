package com.pokemmo.feature.taxonomy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.core.domain.model.TaxonomyTag
import com.pokemmo.core.domain.usecase.GetTaxonomyTreeUseCase
import com.pokemmo.core.domain.usecase.SearchByTaxonomyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TaxonomyViewModel @Inject constructor(
    getTaxonomyTreeUseCase: GetTaxonomyTreeUseCase,
    private val searchByTaxonomyUseCase: SearchByTaxonomyUseCase,
) : ViewModel() {

    val tagTree: StateFlow<List<TaxonomyTag>> = getTaxonomyTreeUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedTagIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedTagIds: StateFlow<Set<Int>> = _selectedTagIds

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredPokemon: Flow<PagingData<Pokemon>> = _selectedTagIds
        .filter { it.isNotEmpty() }
        .flatMapLatest { ids -> searchByTaxonomyUseCase(ids.toList()) }
        .cachedIn(viewModelScope)

    fun toggleTag(tagId: Int) {
        _selectedTagIds.update { current ->
            if (tagId in current) current - tagId else current + tagId
        }
    }

    fun clearSelection() {
        _selectedTagIds.value = emptySet()
    }
}
