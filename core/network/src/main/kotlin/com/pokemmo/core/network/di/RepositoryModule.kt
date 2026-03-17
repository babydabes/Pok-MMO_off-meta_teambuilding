package com.pokemmo.core.network.di

import com.pokemmo.core.domain.repository.*
import com.pokemmo.core.network.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

    @Binds @Singleton
    abstract fun bindTeamRepository(impl: TeamRepositoryImpl): TeamRepository

    @Binds @Singleton
    abstract fun bindTypeRepository(impl: TypeRepositoryImpl): TypeRepository

    @Binds @Singleton
    abstract fun bindTaxonomyRepository(impl: TaxonomyRepositoryImpl): TaxonomyRepository

    @Binds @Singleton
    abstract fun bindSmogonRepository(impl: SmogonRepositoryImpl): SmogonRepository
}
