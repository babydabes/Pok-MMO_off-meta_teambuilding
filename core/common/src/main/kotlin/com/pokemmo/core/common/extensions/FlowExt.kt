package com.pokemmo.core.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import com.pokemmo.core.common.util.Resource

fun <T> Flow<T>.asResource(): Flow<Resource<T>> =
    map<T, Resource<T>> { Resource.Success(it) }
        .catch { emit(Resource.Error(it.message ?: "Unknown error", it)) }
