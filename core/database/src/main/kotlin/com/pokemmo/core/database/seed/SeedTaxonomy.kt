package com.pokemmo.core.database.seed

import com.pokemmo.core.database.entity.PokemonTaxonomyCrossRef
import com.pokemmo.core.database.entity.TaxonomyTagEntity

object SeedTaxonomy {

    val tags: List<TaxonomyTagEntity> = listOf(
        // Top-level categories (parentId = 0)
        TaxonomyTagEntity(1,  "Dragon",     0,  "🐉", 1),
        TaxonomyTagEntity(2,  "Avian",      0,  "🐦", 2),
        TaxonomyTagEntity(3,  "Insect",     0,  "🐛", 3),
        TaxonomyTagEntity(4,  "Aquatic",    0,  "🐟", 4),
        TaxonomyTagEntity(5,  "Feline",     0,  "🐱", 5),
        TaxonomyTagEntity(6,  "Canine",     0,  "🐕", 6),
        TaxonomyTagEntity(7,  "Rodent",     0,  "🐭", 7),
        TaxonomyTagEntity(8,  "Reptile",    0,  "🦎", 8),
        TaxonomyTagEntity(9,  "Plant",      0,  "🌿", 9),
        TaxonomyTagEntity(10, "Mineral",    0,  "⚙️", 10),
        TaxonomyTagEntity(11, "Ghost",      0,  "👻", 11),
        TaxonomyTagEntity(12, "Humanoid",   0,  "🧑", 12),
        TaxonomyTagEntity(13, "Legendary",  0,  "✨", 13),
        TaxonomyTagEntity(14, "Starter",    0,  "🌟", 14),
        TaxonomyTagEntity(15, "Pseudo",     0,  "💫", 15),  // pseudo-legendaries
        // Sub-categories
        TaxonomyTagEntity(101, "Pseudo-Dragon", 1, "🐲", 1),
        TaxonomyTagEntity(102, "Serpent",    1,  "🐍", 2),
        TaxonomyTagEntity(201, "Raptor",     2,  "🦅", 1),
        TaxonomyTagEntity(202, "Songbird",   2,  "🎵", 2),
        TaxonomyTagEntity(203, "Waterfowl",  2,  "🦆", 3),
        TaxonomyTagEntity(301, "Butterfly",  3,  "🦋", 1),
        TaxonomyTagEntity(302, "Beetle",     3,  "🪲", 2),
        TaxonomyTagEntity(401, "Fish",       4,  "🐠", 1),
        TaxonomyTagEntity(402, "Serpentine", 4,  "🐡", 2),
        TaxonomyTagEntity(403, "Crustacean", 4,  "🦀", 3),
    )

    /** Maps each pokemonId to its taxonomy tag IDs. */
    val crossRefs: List<PokemonTaxonomyCrossRef> = listOf(
        // Charizard
        PokemonTaxonomyCrossRef(6, 2),    // Avian
        PokemonTaxonomyCrossRef(6, 8),    // Reptile
        PokemonTaxonomyCrossRef(6, 14),   // Starter
        // Pikachu
        PokemonTaxonomyCrossRef(25, 7),   // Rodent
        // Arcanine
        PokemonTaxonomyCrossRef(59, 6),   // Canine
        // Gyarados
        PokemonTaxonomyCrossRef(130, 4),  // Aquatic
        PokemonTaxonomyCrossRef(130, 8),  // Reptile
        PokemonTaxonomyCrossRef(130, 402),// Serpentine Aquatic
        // Dragonite
        PokemonTaxonomyCrossRef(149, 1),  // Dragon
        PokemonTaxonomyCrossRef(149, 2),  // Avian
        PokemonTaxonomyCrossRef(149, 15), // Pseudo
        PokemonTaxonomyCrossRef(149, 101),// Pseudo-Dragon
        // Feraligatr
        PokemonTaxonomyCrossRef(160, 4),  // Aquatic
        PokemonTaxonomyCrossRef(160, 8),  // Reptile
        PokemonTaxonomyCrossRef(160, 14), // Starter
        // Umbreon
        PokemonTaxonomyCrossRef(197, 6),  // Canine
        // Scizor
        PokemonTaxonomyCrossRef(212, 3),  // Insect
        PokemonTaxonomyCrossRef(212, 302),// Beetle
        // Heracross
        PokemonTaxonomyCrossRef(214, 3),  // Insect
        PokemonTaxonomyCrossRef(214, 302),// Beetle
        // Suicune
        PokemonTaxonomyCrossRef(245, 13), // Legendary
        PokemonTaxonomyCrossRef(245, 6),  // Canine
        // Tyranitar
        PokemonTaxonomyCrossRef(248, 8),  // Reptile
        PokemonTaxonomyCrossRef(248, 15), // Pseudo
        // Swampert
        PokemonTaxonomyCrossRef(260, 4),  // Aquatic
        PokemonTaxonomyCrossRef(260, 14), // Starter
        // Gardevoir
        PokemonTaxonomyCrossRef(282, 12), // Humanoid
        // Flygon
        PokemonTaxonomyCrossRef(330, 1),  // Dragon
        PokemonTaxonomyCrossRef(330, 8),  // Reptile
        // Altaria
        PokemonTaxonomyCrossRef(334, 1),  // Dragon
        PokemonTaxonomyCrossRef(334, 2),  // Avian
        // Salamence
        PokemonTaxonomyCrossRef(373, 1),  // Dragon
        PokemonTaxonomyCrossRef(373, 2),  // Avian
        PokemonTaxonomyCrossRef(373, 15), // Pseudo
        PokemonTaxonomyCrossRef(373, 101),// Pseudo-Dragon
        // Metagross
        PokemonTaxonomyCrossRef(376, 10), // Mineral
        PokemonTaxonomyCrossRef(376, 15), // Pseudo
        // Empoleon
        PokemonTaxonomyCrossRef(395, 4),  // Aquatic
        PokemonTaxonomyCrossRef(395, 2),  // Avian (penguin)
        PokemonTaxonomyCrossRef(395, 14), // Starter
        // Luxray
        PokemonTaxonomyCrossRef(405, 5),  // Feline
        // Roserade
        PokemonTaxonomyCrossRef(407, 9),  // Plant
        // Garchomp
        PokemonTaxonomyCrossRef(445, 1),  // Dragon
        PokemonTaxonomyCrossRef(445, 15), // Pseudo
        PokemonTaxonomyCrossRef(445, 101),// Pseudo-Dragon
        // Lucario
        PokemonTaxonomyCrossRef(448, 12), // Humanoid
        PokemonTaxonomyCrossRef(448, 6),  // Canine
        // Abomasnow
        PokemonTaxonomyCrossRef(460, 9),  // Plant
        // Weavile
        PokemonTaxonomyCrossRef(461, 5),  // Feline
        // Magnezone
        PokemonTaxonomyCrossRef(462, 10), // Mineral
        // Gliscor
        PokemonTaxonomyCrossRef(472, 8),  // Reptile
        PokemonTaxonomyCrossRef(472, 2),  // Avian
        // Rotom-W
        PokemonTaxonomyCrossRef(479, 11), // Ghost
        // Excadrill
        PokemonTaxonomyCrossRef(530, 10), // Mineral
        // Conkeldurr
        PokemonTaxonomyCrossRef(534, 12), // Humanoid
        // Lilligant
        PokemonTaxonomyCrossRef(549, 9),  // Plant
        // Ferrothorn
        PokemonTaxonomyCrossRef(598, 9),  // Plant
        PokemonTaxonomyCrossRef(598, 10), // Mineral
        // Chandelure
        PokemonTaxonomyCrossRef(609, 11), // Ghost
        // Haxorus
        PokemonTaxonomyCrossRef(612, 1),  // Dragon
        // Mandibuzz
        PokemonTaxonomyCrossRef(630, 2),  // Avian
        PokemonTaxonomyCrossRef(630, 201),// Raptor
        // Hydreigon
        PokemonTaxonomyCrossRef(635, 1),  // Dragon
        PokemonTaxonomyCrossRef(635, 15), // Pseudo
        PokemonTaxonomyCrossRef(635, 101),// Pseudo-Dragon
        // Volcarona
        PokemonTaxonomyCrossRef(637, 3),  // Insect
        PokemonTaxonomyCrossRef(637, 301),// Butterfly
        // Landorus
        PokemonTaxonomyCrossRef(645, 13), // Legendary
    )
}
