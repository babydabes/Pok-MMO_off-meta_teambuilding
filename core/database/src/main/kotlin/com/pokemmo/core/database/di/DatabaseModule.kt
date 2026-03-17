package com.pokemmo.core.database.di

import android.content.Context
import androidx.room.Room
import com.pokemmo.core.database.AppDatabase
import com.pokemmo.core.database.dao.*
import com.pokemmo.core.database.seed.DatabaseSeeder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
        CoroutineScope(Dispatchers.IO).launch {
            if (db.pokemonDao().count() == 0) {
                DatabaseSeeder.seed(db)
            }
        }
        return db
    }

    @Provides fun providePokemonDao(db: AppDatabase): PokemonDao = db.pokemonDao()
    @Provides fun provideMoveDao(db: AppDatabase): MoveDao = db.moveDao()
    @Provides fun provideTypeDao(db: AppDatabase): TypeDao = db.typeDao()
    @Provides fun provideTeamDao(db: AppDatabase): TeamDao = db.teamDao()
    @Provides fun provideTaxonomyDao(db: AppDatabase): TaxonomyDao = db.taxonomyDao()
    @Provides fun provideSmogonSetDao(db: AppDatabase): SmogonSetDao = db.smogonSetDao()
}
