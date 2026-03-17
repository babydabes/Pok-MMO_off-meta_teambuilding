package com.pokemmo.core.domain.usecase

import androidx.paging.PagingData
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.core.domain.model.PokemmoTier
import com.pokemmo.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(tier: PokemmoTier? = null): Flow<PagingData<Pokemon>> =
        repository.getPagedPokemon(tier)
}

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(query: String, typeFilter: String? = null): Flow<PagingData<Pokemon>> =
        repository.searchPokemon(query, typeFilter)
}

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(id: Int): Pokemon? = repository.getPokemonById(id)
}

class SearchByTaxonomyUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(tagIds: List<Int>): Flow<PagingData<Pokemon>> =
        repository.searchByTaxonomyTags(tagIds)
}
