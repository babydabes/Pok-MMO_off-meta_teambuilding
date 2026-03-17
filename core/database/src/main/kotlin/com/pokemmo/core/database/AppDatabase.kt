package com.pokemmo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokemmo.core.database.dao.*
import com.pokemmo.core.database.entity.*

@Database(
    entities = [
        PokemonEntity::class,
        PokemonFtsEntity::class,
        MoveEntity::class,
        MoveLearnsetEntity::class,
        TypeEntity::class,
        TypeEffectivenessEntity::class,
        TeamEntity::class,
        TeamMemberEntity::class,
        TaxonomyTagEntity::class,
        PokemonTaxonomyCrossRef::class,
        SmogonSetEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun moveDao(): MoveDao
    abstract fun typeDao(): TypeDao
    abstract fun teamDao(): TeamDao
    abstract fun taxonomyDao(): TaxonomyDao
    abstract fun smogonSetDao(): SmogonSetDao

    companion object {
        const val DATABASE_NAME = "pokemmo.db"
    }
}
