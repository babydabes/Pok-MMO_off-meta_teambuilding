package com.pokemmo.core.network.api

import com.pokemmo.core.network.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 649,
        @Query("offset") offset: Int = 0,
    ): PokeApiListDto

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpeciesDto

    @GET("move/{id}")
    suspend fun getMove(@Path("id") id: Int): MoveDto

    @GET("type/{id}")
    suspend fun getType(@Path("id") id: Int): TypeDto
}
