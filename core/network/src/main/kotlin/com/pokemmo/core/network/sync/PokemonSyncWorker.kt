package com.pokemmo.core.network.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pokemmo.core.domain.repository.PokemonRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PokemonSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val pokemonRepository: PokemonRepository,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return pokemonRepository.syncFromNetwork().fold(
            onSuccess = { Result.success() },
            onFailure = {
                if (runAttemptCount < 3) Result.retry()
                else Result.failure()
            }
        )
    }
}
