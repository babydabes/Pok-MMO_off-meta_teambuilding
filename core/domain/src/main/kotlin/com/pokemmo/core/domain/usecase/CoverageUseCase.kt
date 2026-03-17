package com.pokemmo.core.domain.usecase

import com.pokemmo.core.domain.model.CoverageResult
import com.pokemmo.core.domain.model.MoveCategory
import com.pokemmo.core.domain.model.Team
import com.pokemmo.core.domain.model.Type
import com.pokemmo.core.domain.model.TypeChart
import com.pokemmo.core.domain.repository.TypeRepository
import javax.inject.Inject

class GetCoverageUseCase @Inject constructor(
    private val typeRepository: TypeRepository,
) {
    suspend operator fun invoke(team: Team): CoverageResult {
        val chart = typeRepository.getTypeChart()
        val allTypes = chart.types

        // Offensive: best multiplier any non-Status move achieves against each defending type
        val offensiveMults = mutableMapOf<String, Float>()
        for (defType in allTypes) {
            var best = 0f
            for (member in team.members.filterNotNull()) {
                for (move in member.moves.filterNotNull()) {
                    if (move.category == MoveCategory.Status || move.power == 0) continue
                    val mult = chart.multiplier(move.type, defType)
                    if (mult > best) best = mult
                }
            }
            offensiveMults[defType.name] = best
        }

        // Defensive: for each attacking type, count weak/resist/immune across team
        val weaknesses = mutableMapOf<String, Int>()
        val resistances = mutableMapOf<String, Int>()
        val immunities = mutableMapOf<String, Int>()

        for (atkType in allTypes) {
            var wCount = 0; var rCount = 0; var iCount = 0
            for (member in team.members.filterNotNull()) {
                val combined = chart.combinedDefense(member.pokemon.types, atkType)
                when {
                    combined == 0f -> iCount++
                    combined < 1f  -> rCount++
                    combined > 1f  -> wCount++
                }
            }
            weaknesses[atkType.name] = wCount
            resistances[atkType.name] = rCount
            immunities[atkType.name] = iCount
        }

        return CoverageResult(offensiveMults, weaknesses, resistances, immunities)
    }
}

class GetSmogonSetsUseCase @Inject constructor(
    private val smogonRepository: com.pokemmo.core.domain.repository.SmogonRepository,
) {
    suspend operator fun invoke(pokemonId: Int, tier: String? = null) =
        smogonRepository.getSetsForPokemon(pokemonId, tier)
}

class GetTaxonomyTreeUseCase @Inject constructor(
    private val taxonomyRepository: com.pokemmo.core.domain.repository.TaxonomyRepository,
) {
    operator fun invoke() = taxonomyRepository.observeTagTree()
}
