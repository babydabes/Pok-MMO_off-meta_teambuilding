package com.pokemmo.core.database.seed

import com.pokemmo.core.database.AppDatabase

/**
 * Populates the database on first creation with:
 * - Types + Gen 5 type chart
 * - 40 curated PokéMMO OU/UU/NU Pokémon
 * - Key moves (60+)
 * - Taxonomy tree (15 top-level, 10 sub-categories, 60+ cross-refs)
 * - Smogon sets for all OU Pokémon
 *
 * Called from [DatabaseModule] in a background coroutine on first app launch.
 */
object DatabaseSeeder {

    suspend fun seed(db: AppDatabase) {
        db.typeDao().upsertTypes(SeedTypes.types)
        db.typeDao().upsertEffectiveness(SeedTypes.effectiveness)

        db.pokemonDao().upsertAll(SeedPokemon.pokemon)

        db.moveDao().upsertAll(SeedMoves.moves)

        db.taxonomyDao().upsertTags(SeedTaxonomy.tags)
        db.taxonomyDao().upsertCrossRefs(SeedTaxonomy.crossRefs)

        db.smogonSetDao().upsertAll(SeedSmogonSets.sets)
    }
}
