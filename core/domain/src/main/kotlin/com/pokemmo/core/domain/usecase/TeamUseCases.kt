package com.pokemmo.core.domain.usecase

import com.pokemmo.core.domain.model.Team
import com.pokemmo.core.domain.model.TeamMember
import com.pokemmo.core.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTeamsUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    operator fun invoke(): Flow<List<Team>> = repository.observeAllTeams()
}

class GetTeamUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    operator fun invoke(teamId: Long): Flow<Team?> = repository.observeTeam(teamId)
}

class CreateTeamUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(name: String, format: String = "PokéMMO-OU"): Long =
        repository.createTeam(name, format)
}

class SaveTeamUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(team: Team) = repository.saveTeam(team)
}

class AddTeamMemberUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamId: Long, pokemonId: Int, slot: Int): Result<Unit> =
        repository.addMember(teamId, pokemonId, slot)
}

class UpdateTeamMemberUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamId: Long, member: TeamMember): Result<Unit> =
        repository.updateMember(teamId, member)
}

class RemoveTeamMemberUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamId: Long, slot: Int) =
        repository.removeMember(teamId, slot)
}

class ArchiveTeamUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamId: Long) = repository.archiveTeam(teamId)
}

class ImportShowdownPasteUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(paste: String): Result<Team> =
        repository.importFromShowdown(paste)
}

class ExportShowdownPasteUseCase @Inject constructor(
    private val repository: TeamRepository,
) {
    operator fun invoke(team: Team): String = repository.exportToShowdown(team)
}
