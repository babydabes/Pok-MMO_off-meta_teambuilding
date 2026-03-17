package com.pokemmo.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiListDto(
    val count: Int,
    val results: List<NamedResourceDto>,
)

@Serializable
data class NamedResourceDto(
    val name: String,
    val url: String,
)

@Serializable
data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeDto>,
    val abilities: List<PokemonAbilityDto>,
    val stats: List<PokemonStatDto>,
    val sprites: SpritesDto,
    val moves: List<PokemonMoveDto>,
)

@Serializable
data class PokemonTypeDto(
    val slot: Int,
    val type: NamedResourceDto,
)

@Serializable
data class PokemonAbilityDto(
    val slot: Int,
    @SerialName("is_hidden") val isHidden: Boolean,
    val ability: NamedResourceDto,
)

@Serializable
data class PokemonStatDto(
    @SerialName("base_stat") val baseStat: Int,
    val stat: NamedResourceDto,
)

@Serializable
data class SpritesDto(
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_shiny") val frontShiny: String?,
)

@Serializable
data class PokemonMoveDto(
    val move: NamedResourceDto,
    @SerialName("version_group_details") val versionGroupDetails: List<MoveVersionGroupDto>,
)

@Serializable
data class MoveVersionGroupDto(
    @SerialName("level_learned_at") val levelLearnedAt: Int,
    @SerialName("move_learn_method") val moveLearnMethod: NamedResourceDto,
    @SerialName("version_group") val versionGroup: NamedResourceDto,
)

@Serializable
data class PokemonSpeciesDto(
    val id: Int,
    val name: String,
    @SerialName("is_legendary") val isLegendary: Boolean,
    @SerialName("is_mythical") val isMythical: Boolean,
    @SerialName("generation") val generation: NamedResourceDto,
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextDto>,
)

@Serializable
data class FlavorTextDto(
    @SerialName("flavor_text") val flavorText: String,
    val language: NamedResourceDto,
    val version: NamedResourceDto,
)

@Serializable
data class MoveDto(
    val id: Int,
    val name: String,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int,
    val priority: Int,
    @SerialName("damage_class") val damageClass: NamedResourceDto,
    val type: NamedResourceDto,
    @SerialName("effect_entries") val effectEntries: List<MoveEffectDto>,
    val generation: NamedResourceDto,
)

@Serializable
data class MoveEffectDto(
    @SerialName("short_effect") val shortEffect: String,
    val language: NamedResourceDto,
)

@Serializable
data class TypeDto(
    val id: Int,
    val name: String,
    @SerialName("damage_relations") val damageRelations: DamageRelationsDto,
)

@Serializable
data class DamageRelationsDto(
    @SerialName("double_damage_to") val doubleDamageTo: List<NamedResourceDto>,
    @SerialName("half_damage_to") val halfDamageTo: List<NamedResourceDto>,
    @SerialName("no_damage_to") val noDamageTo: List<NamedResourceDto>,
)
