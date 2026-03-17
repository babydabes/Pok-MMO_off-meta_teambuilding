package com.pokemmo.feature.teambuilder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemmo.core.domain.model.Team
import com.pokemmo.core.domain.usecase.CreateTeamUseCase
import com.pokemmo.core.domain.usecase.GetAllTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    getAllTeamsUseCase: GetAllTeamsUseCase,
    private val createTeamUseCase: CreateTeamUseCase,
) : ViewModel() {

    val teams: Flow<List<Team>> = getAllTeamsUseCase()

    fun createNewTeam(onCreated: (Long) -> Unit) {
        viewModelScope.launch {
            val id = createTeamUseCase("New Team")
            onCreated(id)
        }
    }
}
