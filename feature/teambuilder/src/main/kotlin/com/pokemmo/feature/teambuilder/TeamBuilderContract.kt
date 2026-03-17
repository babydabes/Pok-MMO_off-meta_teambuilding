package com.pokemmo.feature.teambuilder

import androidx.paging.PagingData
import com.pokemmo.core.domain.model.*

data class TeamBuilderUiState(
    val team: Team? = null,
    val coverage: CoverageResult? = null,
    val isSearchOpen: Boolean = false,
    val activeSlot: Int = -1,
    val searchQuery: String = "",
    val searchResults: PagingData<Pokemon> = PagingData.empty(),
    val suggestedSets: List<SmogonSet> = emptyList(),
    val activeSuggestionSlot: Int = -1,
    val isLoading: Boolean = false,
    val expandedMemberSlot: Int = -1,
    val showImportDialog: Boolean = false,
)

sealed interface TeamBuilderIntent {
    data class SelectSlot(val slot: Int) : TeamBuilderIntent
    data class SearchQueryChanged(val query: String) : TeamBuilderIntent
    data class AddPokemon(val pokemonId: Int, val slot: Int) : TeamBuilderIntent
    data class RemoveMember(val slot: Int) : TeamBuilderIntent
    data class ApplySmogonSet(val slot: Int, val set: SmogonSet) : TeamBuilderIntent
    data class ExpandMember(val slot: Int) : TeamBuilderIntent
    data object ExportTeam : TeamBuilderIntent
    data class ImportPaste(val paste: String) : TeamBuilderIntent
    data object DismissSearch : TeamBuilderIntent
    data object ToggleImportDialog : TeamBuilderIntent
    data class ToggleShiny(val slot: Int) : TeamBuilderIntent
}

sealed interface TeamBuilderEffect {
    data class ShowError(val message: String) : TeamBuilderEffect
    data class ShareExport(val paste: String) : TeamBuilderEffect
    data class ShowSnackbar(val message: String) : TeamBuilderEffect
}
