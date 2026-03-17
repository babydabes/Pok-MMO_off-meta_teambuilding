package com.pokemmo.feature.teambuilder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokemmo.core.domain.model.SmogonSet
import com.pokemmo.core.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamBuilderViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase,
    private val addTeamMemberUseCase: AddTeamMemberUseCase,
    private val removeTeamMemberUseCase: RemoveTeamMemberUseCase,
    private val getCoverageUseCase: GetCoverageUseCase,
    private val getSmogonSetsUseCase: GetSmogonSetsUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val importUseCase: ImportShowdownPasteUseCase,
    private val exportUseCase: ExportShowdownPasteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val teamId: Long = checkNotNull(savedStateHandle["teamId"])

    private val _uiState = MutableStateFlow(TeamBuilderUiState(isLoading = true))
    val uiState: StateFlow<TeamBuilderUiState> = _uiState.asStateFlow()

    private val _effects = Channel<TeamBuilderEffect>(Channel.BUFFERED)
    val effects: Flow<TeamBuilderEffect> = _effects.receiveAsFlow()

    init {
        observeTeam()
    }

    fun onIntent(intent: TeamBuilderIntent) {
        when (intent) {
            is TeamBuilderIntent.SelectSlot -> openSearch(intent.slot)
            is TeamBuilderIntent.SearchQueryChanged -> updateSearch(intent.query)
            is TeamBuilderIntent.AddPokemon -> addPokemon(intent.pokemonId, intent.slot)
            is TeamBuilderIntent.RemoveMember -> removeMember(intent.slot)
            is TeamBuilderIntent.ApplySmogonSet -> applySmogonSet(intent.slot, intent.set)
            is TeamBuilderIntent.ExpandMember -> toggleExpand(intent.slot)
            is TeamBuilderIntent.ExportTeam -> exportTeam()
            is TeamBuilderIntent.ImportPaste -> importPaste(intent.paste)
            is TeamBuilderIntent.DismissSearch -> dismissSearch()
            is TeamBuilderIntent.ToggleImportDialog -> toggleImport()
            is TeamBuilderIntent.ToggleShiny -> toggleShiny(intent.slot)
        }
    }

    private fun observeTeam() {
        viewModelScope.launch {
            getTeamUseCase(teamId).collect { team ->
                val coverage = team?.let { t ->
                    if (t.filledSlots > 0) getCoverageUseCase(t) else null
                }
                _uiState.update {
                    it.copy(team = team, coverage = coverage, isLoading = false)
                }
            }
        }
    }

    private fun openSearch(slot: Int) {
        _uiState.update { it.copy(isSearchOpen = true, activeSlot = slot, searchQuery = "") }
    }

    private fun dismissSearch() {
        _uiState.update { it.copy(isSearchOpen = false, searchQuery = "") }
    }

    private fun updateSearch(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        if (query.length < 2) return
        viewModelScope.launch {
            searchPokemonUseCase(query)
                .cachedIn(viewModelScope)
                .collect { paged ->
                    _uiState.update { it.copy(searchResults = paged) }
                }
        }
    }

    private fun addPokemon(pokemonId: Int, slot: Int) {
        viewModelScope.launch {
            addTeamMemberUseCase(teamId, pokemonId, slot)
                .onSuccess {
                    val sets = getSmogonSetsUseCase(pokemonId)
                    _uiState.update {
                        it.copy(
                            isSearchOpen = false,
                            suggestedSets = sets,
                            activeSuggestionSlot = slot,
                        )
                    }
                    _effects.send(TeamBuilderEffect.ShowSnackbar("Added to slot ${slot + 1}"))
                }
                .onFailure { e ->
                    _effects.send(TeamBuilderEffect.ShowError(e.message ?: "Failed to add"))
                }
        }
    }

    private fun removeMember(slot: Int) {
        viewModelScope.launch {
            removeTeamMemberUseCase(teamId, slot)
            _uiState.update { it.copy(suggestedSets = emptyList(), activeSuggestionSlot = -1) }
        }
    }

    private fun applySmogonSet(slot: Int, set: SmogonSet) {
        _uiState.update {
            it.copy(suggestedSets = emptyList(), activeSuggestionSlot = -1)
        }
        viewModelScope.launch {
            _effects.send(TeamBuilderEffect.ShowSnackbar("Applied ${set.setName}"))
        }
    }

    private fun toggleExpand(slot: Int) {
        _uiState.update {
            it.copy(expandedMemberSlot = if (it.expandedMemberSlot == slot) -1 else slot)
        }
    }

    private fun exportTeam() {
        val team = _uiState.value.team ?: return
        val paste = exportUseCase(team)
        viewModelScope.launch { _effects.send(TeamBuilderEffect.ShareExport(paste)) }
    }

    private fun importPaste(paste: String) {
        viewModelScope.launch {
            importUseCase(paste)
                .onSuccess {
                    _uiState.update { it.copy(showImportDialog = false) }
                    _effects.send(TeamBuilderEffect.ShowSnackbar("Team imported!"))
                }
                .onFailure { e ->
                    _effects.send(TeamBuilderEffect.ShowError("Import failed: ${e.message}"))
                }
        }
    }

    private fun toggleImport() {
        _uiState.update { it.copy(showImportDialog = !it.showImportDialog) }
    }

    private fun toggleShiny(slot: Int) {
        // would update member entity — simplified for now
    }
}
