package com.pokemmo.core.network.repository

import com.pokemmo.core.database.dao.TypeDao
import com.pokemmo.core.domain.model.Type
import com.pokemmo.core.domain.model.TypeChart
import com.pokemmo.core.domain.repository.TypeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypeRepositoryImpl @Inject constructor(
    private val typeDao: TypeDao,
) : TypeRepository {

    override suspend fun getAllTypes(): List<Type> =
        typeDao.getAllTypes().map { Type(it.id, it.name, it.colorHex) }

    override suspend fun getTypeChart(): TypeChart {
        val types = typeDao.getAllTypes().map { Type(it.id, it.name, it.colorHex) }
        val effectiveness = typeDao.getAllEffectiveness()
            .associate { (it.attackingTypeId to it.defendingTypeId) to it.multiplier }
        return TypeChart(types, effectiveness)
    }
}
