package com.pokemmo.core.network.repository

import com.pokemmo.core.database.dao.TaxonomyDao
import com.pokemmo.core.domain.model.TaxonomyTag
import com.pokemmo.core.domain.repository.TaxonomyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaxonomyRepositoryImpl @Inject constructor(
    private val taxonomyDao: TaxonomyDao,
) : TaxonomyRepository {

    override fun observeTagTree(): Flow<List<TaxonomyTag>> =
        taxonomyDao.observeAllTags().map { entities ->
            val all = entities.map { e ->
                TaxonomyTag(e.id, e.name, e.parentId, e.iconEmoji, e.sortOrder)
            }
            val byParent = all.groupBy { it.parentId }
            // Top-level tags (parentId == 0) with children attached
            byParent[0].orEmpty().map { parent ->
                parent.copy(children = byParent[parent.id].orEmpty())
            }.sortedBy { it.sortOrder }
        }

    override suspend fun getAllTags(): List<TaxonomyTag> =
        taxonomyDao.getAllTags().map { e ->
            TaxonomyTag(e.id, e.name, e.parentId, e.iconEmoji, e.sortOrder)
        }

    override suspend fun getTagsForPokemon(pokemonId: Int): List<TaxonomyTag> =
        taxonomyDao.getTagsForPokemon(pokemonId).map { e ->
            TaxonomyTag(e.id, e.name, e.parentId, e.iconEmoji, e.sortOrder)
        }
}
