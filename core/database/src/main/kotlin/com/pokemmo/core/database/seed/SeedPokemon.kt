package com.pokemmo.core.database.seed

import com.pokemmo.core.database.entity.PokemonEntity

/**
 * Hand-curated roster of 40 Pokémon relevant to PokéMMO Gen 5 metagame (OU, UU, NU).
 * Stats are accurate to BW2 Bulbapedia data.
 * Sprites use the PokeAPI CDN (available offline after first cache by Coil).
 */
object SeedPokemon {

    private fun sprite(id: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    private fun shiny(id: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"

    @Suppress("LongMethod")
    val pokemon: List<PokemonEntity> = listOf(

        // ===== OU TIER =====
        PokemonEntity(
            id = 6, name = "charizard", displayName = "Charizard",
            generation = 1, baseHp = 78, baseAtk = 84, baseDef = 78,
            baseSpAtk = 109, baseSpDef = 85, baseSpeed = 100, bst = 534,
            type1 = "Fire", type2 = "Flying",
            ability1 = "Blaze", ability2 = "", hiddenAbility = "Solar Power",
            spriteUrl = sprite(6), spriteShinyUrl = shiny(6),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.7f, weight = 90.5f, flavorText = "Spits fire so hot it melts boulders."
        ),
        PokemonEntity(
            id = 25, name = "pikachu", displayName = "Pikachu",
            generation = 1, baseHp = 35, baseAtk = 55, baseDef = 40,
            baseSpAtk = 50, baseSpDef = 50, baseSpeed = 90, bst = 320,
            type1 = "Electric", type2 = "",
            ability1 = "Static", ability2 = "", hiddenAbility = "Lightning Rod",
            spriteUrl = sprite(25), spriteShinyUrl = shiny(25),
            pokemmoTier = "NU", isLegendary = false, isMythical = false,
            height = 0.4f, weight = 6.0f, flavorText = "The electric sacs in its cheeks discharge electricity."
        ),
        PokemonEntity(
            id = 59, name = "arcanine", displayName = "Arcanine",
            generation = 1, baseHp = 90, baseAtk = 110, baseDef = 80,
            baseSpAtk = 100, baseSpDef = 80, baseSpeed = 95, bst = 555,
            type1 = "Fire", type2 = "",
            ability1 = "Intimidate", ability2 = "Flash Fire", hiddenAbility = "Justified",
            spriteUrl = sprite(59), spriteShinyUrl = shiny(59),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.9f, weight = 155.0f, flavorText = "Renowned for its beauty and swiftness."
        ),
        PokemonEntity(
            id = 130, name = "gyarados", displayName = "Gyarados",
            generation = 1, baseHp = 95, baseAtk = 125, baseDef = 79,
            baseSpAtk = 60, baseSpDef = 100, baseSpeed = 81, bst = 540,
            type1 = "Water", type2 = "Flying",
            ability1 = "Intimidate", ability2 = "", hiddenAbility = "Moxie",
            spriteUrl = sprite(130), spriteShinyUrl = shiny(130),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 6.5f, weight = 235.0f, flavorText = "Rarely seen in the wild. Extremely aggressive."
        ),
        PokemonEntity(
            id = 149, name = "dragonite", displayName = "Dragonite",
            generation = 1, baseHp = 91, baseAtk = 134, baseDef = 95,
            baseSpAtk = 100, baseSpDef = 100, baseSpeed = 80, bst = 600,
            type1 = "Dragon", type2 = "Flying",
            ability1 = "Inner Focus", ability2 = "", hiddenAbility = "Multiscale",
            spriteUrl = sprite(149), spriteShinyUrl = shiny(149),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 2.2f, weight = 210.0f, flavorText = "A gentle soul. Guides ships through storms."
        ),
        PokemonEntity(
            id = 160, name = "feraligatr", displayName = "Feraligatr",
            generation = 2, baseHp = 85, baseAtk = 105, baseDef = 100,
            baseSpAtk = 79, baseSpDef = 83, baseSpeed = 78, bst = 530,
            type1 = "Water", type2 = "",
            ability1 = "Torrent", ability2 = "", hiddenAbility = "Sheer Force",
            spriteUrl = sprite(160), spriteShinyUrl = shiny(160),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 2.3f, weight = 88.8f, flavorText = "Clamps its powerful jaws and shakes to inflict damage."
        ),
        PokemonEntity(
            id = 197, name = "umbreon", displayName = "Umbreon",
            generation = 2, baseHp = 95, baseAtk = 65, baseDef = 110,
            baseSpAtk = 60, baseSpDef = 130, baseSpeed = 65, bst = 525,
            type1 = "Dark", type2 = "",
            ability1 = "Synchronize", ability2 = "", hiddenAbility = "Inner Focus",
            spriteUrl = sprite(197), spriteShinyUrl = shiny(197),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.0f, weight = 27.0f, flavorText = "Rings on its body glow when it faces danger."
        ),
        PokemonEntity(
            id = 212, name = "scizor", displayName = "Scizor",
            generation = 2, baseHp = 70, baseAtk = 130, baseDef = 100,
            baseSpAtk = 55, baseSpDef = 80, baseSpeed = 65, bst = 500,
            type1 = "Bug", type2 = "Steel",
            ability1 = "Swarm", ability2 = "Technician", hiddenAbility = "Light Metal",
            spriteUrl = sprite(212), spriteShinyUrl = shiny(212),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.8f, weight = 118.0f, flavorText = "Its pincers are so powerful they can punch through steel plates."
        ),
        PokemonEntity(
            id = 214, name = "heracross", displayName = "Heracross",
            generation = 2, baseHp = 80, baseAtk = 125, baseDef = 75,
            baseSpAtk = 40, baseSpDef = 95, baseSpeed = 85, bst = 500,
            type1 = "Bug", type2 = "Fighting",
            ability1 = "Swarm", ability2 = "Guts", hiddenAbility = "Moxie",
            spriteUrl = sprite(214), spriteShinyUrl = shiny(214),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.5f, weight = 54.0f, flavorText = "Uses its powerful horn to hurl foes."
        ),
        PokemonEntity(
            id = 245, name = "suicune", displayName = "Suicune",
            generation = 2, baseHp = 100, baseAtk = 75, baseDef = 115,
            baseSpAtk = 90, baseSpDef = 115, baseSpeed = 85, bst = 580,
            type1 = "Water", type2 = "",
            ability1 = "Pressure", ability2 = "", hiddenAbility = "Inner Focus",
            spriteUrl = sprite(245), spriteShinyUrl = shiny(245),
            pokemmoTier = "OU", isLegendary = true, isMythical = false,
            height = 2.0f, weight = 187.0f, flavorText = "Runs across the surface of water with blinding speed."
        ),
        PokemonEntity(
            id = 248, name = "tyranitar", displayName = "Tyranitar",
            generation = 2, baseHp = 100, baseAtk = 134, baseDef = 110,
            baseSpAtk = 95, baseSpDef = 100, baseSpeed = 61, bst = 600,
            type1 = "Rock", type2 = "Dark",
            ability1 = "Sand Stream", ability2 = "", hiddenAbility = "Unnerve",
            spriteUrl = sprite(248), spriteShinyUrl = shiny(248),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 2.0f, weight = 202.0f, flavorText = "Its body is so rugged that even dynamite can't scratch it."
        ),
        PokemonEntity(
            id = 260, name = "swampert", displayName = "Swampert",
            generation = 3, baseHp = 100, baseAtk = 110, baseDef = 90,
            baseSpAtk = 85, baseSpDef = 90, baseSpeed = 60, bst = 535,
            type1 = "Water", type2 = "Ground",
            ability1 = "Torrent", ability2 = "", hiddenAbility = "Damp",
            spriteUrl = sprite(260), spriteShinyUrl = shiny(260),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.5f, weight = 81.9f, flavorText = "Predicts storms by sensing subtle differences in sound."
        ),
        PokemonEntity(
            id = 282, name = "gardevoir", displayName = "Gardevoir",
            generation = 3, baseHp = 68, baseAtk = 65, baseDef = 65,
            baseSpAtk = 125, baseSpDef = 115, baseSpeed = 80, bst = 518,
            type1 = "Psychic", type2 = "",
            ability1 = "Synchronize", ability2 = "Trace", hiddenAbility = "Telepathy",
            spriteUrl = sprite(282), spriteShinyUrl = shiny(282),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.6f, weight = 48.4f, flavorText = "Senses the emotions of its trainer and protects them with its life."
        ),
        PokemonEntity(
            id = 330, name = "flygon", displayName = "Flygon",
            generation = 3, baseHp = 80, baseAtk = 100, baseDef = 80,
            baseSpAtk = 80, baseSpDef = 80, baseSpeed = 100, bst = 520,
            type1 = "Ground", type2 = "Dragon",
            ability1 = "Levitate", ability2 = "", hiddenAbility = "",
            spriteUrl = sprite(330), spriteShinyUrl = shiny(330),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 2.0f, weight = 82.0f, flavorText = "Known as the Desert Spirit for the mirages it creates."
        ),
        PokemonEntity(
            id = 334, name = "altaria", displayName = "Altaria",
            generation = 3, baseHp = 75, baseAtk = 70, baseDef = 90,
            baseSpAtk = 70, baseSpDef = 105, baseSpeed = 80, bst = 490,
            type1 = "Dragon", type2 = "Flying",
            ability1 = "Natural Cure", ability2 = "", hiddenAbility = "Cloud Nine",
            spriteUrl = sprite(334), spriteShinyUrl = shiny(334),
            pokemmoTier = "NU", isLegendary = false, isMythical = false,
            height = 1.1f, weight = 20.6f, flavorText = "Sings in a beautiful soprano voice that soothes the listener."
        ),
        PokemonEntity(
            id = 373, name = "salamence", displayName = "Salamence",
            generation = 3, baseHp = 95, baseAtk = 135, baseDef = 80,
            baseSpAtk = 110, baseSpDef = 80, baseSpeed = 100, bst = 600,
            type1 = "Dragon", type2 = "Flying",
            ability1 = "Intimidate", ability2 = "", hiddenAbility = "Moxie",
            spriteUrl = sprite(373), spriteShinyUrl = shiny(373),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.5f, weight = 102.6f, flavorText = "Flies proudly through the sky and scorches those who challenge it."
        ),
        PokemonEntity(
            id = 376, name = "metagross", displayName = "Metagross",
            generation = 3, baseHp = 80, baseAtk = 135, baseDef = 130,
            baseSpAtk = 95, baseSpDef = 90, baseSpeed = 70, bst = 600,
            type1 = "Steel", type2 = "Psychic",
            ability1 = "Clear Body", ability2 = "", hiddenAbility = "Light Metal",
            spriteUrl = sprite(376), spriteShinyUrl = shiny(376),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.6f, weight = 550.0f, flavorText = "Possesses four brains and can calculate at 10 million computations per second."
        ),
        PokemonEntity(
            id = 395, name = "empoleon", displayName = "Empoleon",
            generation = 4, baseHp = 84, baseAtk = 86, baseDef = 88,
            baseSpAtk = 111, baseSpDef = 101, baseSpeed = 60, bst = 530,
            type1 = "Water", type2 = "Steel",
            ability1 = "Torrent", ability2 = "", hiddenAbility = "Defiant",
            spriteUrl = sprite(395), spriteShinyUrl = shiny(395),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.7f, weight = 84.5f, flavorText = "Its wings slice steel and have been known to cut ships in half."
        ),
        PokemonEntity(
            id = 405, name = "luxray", displayName = "Luxray",
            generation = 4, baseHp = 80, baseAtk = 120, baseDef = 79,
            baseSpAtk = 95, baseSpDef = 79, baseSpeed = 70, bst = 523,
            type1 = "Electric", type2 = "",
            ability1 = "Rivalry", ability2 = "Intimidate", hiddenAbility = "Guts",
            spriteUrl = sprite(405), spriteShinyUrl = shiny(405),
            pokemmoTier = "NU", isLegendary = false, isMythical = false,
            height = 1.4f, weight = 42.0f, flavorText = "Its powerful eyes see through walls to track prey."
        ),
        PokemonEntity(
            id = 407, name = "roserade", displayName = "Roserade",
            generation = 4, baseHp = 60, baseAtk = 70, baseDef = 65,
            baseSpAtk = 125, baseSpDef = 105, baseSpeed = 90, bst = 515,
            type1 = "Grass", type2 = "Poison",
            ability1 = "Natural Cure", ability2 = "Poison Point", hiddenAbility = "Technician",
            spriteUrl = sprite(407), spriteShinyUrl = shiny(407),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 0.9f, weight = 14.5f, flavorText = "With the bouquets on its hands, it puts foes to sleep then attacks."
        ),
        PokemonEntity(
            id = 445, name = "garchomp", displayName = "Garchomp",
            generation = 4, baseHp = 108, baseAtk = 130, baseDef = 95,
            baseSpAtk = 80, baseSpDef = 85, baseSpeed = 102, bst = 600,
            type1 = "Dragon", type2 = "Ground",
            ability1 = "Sand Veil", ability2 = "", hiddenAbility = "Rough Skin",
            spriteUrl = sprite(445), spriteShinyUrl = shiny(445),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.9f, weight = 95.0f, flavorText = "Moves like a jet plane. Can fly as fast as 1400 km/h."
        ),
        PokemonEntity(
            id = 448, name = "lucario", displayName = "Lucario",
            generation = 4, baseHp = 70, baseAtk = 110, baseDef = 70,
            baseSpAtk = 115, baseSpDef = 70, baseSpeed = 90, bst = 525,
            type1 = "Fighting", type2 = "Steel",
            ability1 = "Steadfast", ability2 = "Inner Focus", hiddenAbility = "Justified",
            spriteUrl = sprite(448), spriteShinyUrl = shiny(448),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.2f, weight = 54.0f, flavorText = "Senses auras on everything, and reads its opponents' movements."
        ),
        PokemonEntity(
            id = 460, name = "abomasnow", displayName = "Abomasnow",
            generation = 4, baseHp = 90, baseAtk = 92, baseDef = 75,
            baseSpAtk = 92, baseSpDef = 85, baseSpeed = 60, bst = 494,
            type1 = "Grass", type2 = "Ice",
            ability1 = "Snow Warning", ability2 = "", hiddenAbility = "Soundproof",
            spriteUrl = sprite(460), spriteShinyUrl = shiny(460),
            pokemmoTier = "NU", isLegendary = false, isMythical = false,
            height = 2.2f, weight = 135.5f, flavorText = "It whips up blizzards whenever enemies approach."
        ),
        PokemonEntity(
            id = 461, name = "weavile", displayName = "Weavile",
            generation = 4, baseHp = 70, baseAtk = 120, baseDef = 65,
            baseSpAtk = 45, baseSpDef = 85, baseSpeed = 125, bst = 510,
            type1 = "Dark", type2 = "Ice",
            ability1 = "Pressure", ability2 = "", hiddenAbility = "Pickpocket",
            spriteUrl = sprite(461), spriteShinyUrl = shiny(461),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.1f, weight = 34.0f, flavorText = "Sharp claws allow it to cut through ice with ease."
        ),
        PokemonEntity(
            id = 462, name = "magnezone", displayName = "Magnezone",
            generation = 4, baseHp = 70, baseAtk = 70, baseDef = 115,
            baseSpAtk = 130, baseSpDef = 90, baseSpeed = 60, bst = 535,
            type1 = "Electric", type2 = "Steel",
            ability1 = "Magnet Pull", ability2 = "Sturdy", hiddenAbility = "Analytic",
            spriteUrl = sprite(462), spriteShinyUrl = shiny(462),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.2f, weight = 180.0f, flavorText = "Generates a forceful magnetic field to trap Steel types."
        ),
        PokemonEntity(
            id = 472, name = "gliscor", displayName = "Gliscor",
            generation = 4, baseHp = 75, baseAtk = 95, baseDef = 125,
            baseSpAtk = 45, baseSpDef = 75, baseSpeed = 95, bst = 510,
            type1 = "Ground", type2 = "Flying",
            ability1 = "Hyper Cutter", ability2 = "Sand Veil", hiddenAbility = "Poison Heal",
            spriteUrl = sprite(472), spriteShinyUrl = shiny(472),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 2.0f, weight = 42.5f, flavorText = "Hangs from tree branches to scout for prey below."
        ),
        PokemonEntity(
            id = 479, name = "rotom", displayName = "Rotom-W",
            generation = 4, baseHp = 50, baseAtk = 65, baseDef = 107,
            baseSpAtk = 105, baseSpDef = 107, baseSpeed = 86, bst = 520,
            type1 = "Electric", type2 = "Water",
            ability1 = "Levitate", ability2 = "", hiddenAbility = "",
            spriteUrl = sprite(479), spriteShinyUrl = shiny(479),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 0.3f, weight = 0.3f, flavorText = "A Rotom that has fused with a washing machine appliance."
        ),
        PokemonEntity(
            id = 530, name = "excadrill", displayName = "Excadrill",
            generation = 5, baseHp = 110, baseAtk = 135, baseDef = 60,
            baseSpAtk = 50, baseSpDef = 65, baseSpeed = 88, bst = 508,
            type1 = "Ground", type2 = "Steel",
            ability1 = "Sand Rush", ability2 = "Sand Force", hiddenAbility = "Mold Breaker",
            spriteUrl = sprite(530), spriteShinyUrl = shiny(530),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 0.7f, weight = 40.4f, flavorText = "Uses its steel drill to break through bedrock."
        ),
        PokemonEntity(
            id = 534, name = "conkeldurr", displayName = "Conkeldurr",
            generation = 5, baseHp = 105, baseAtk = 140, baseDef = 95,
            baseSpAtk = 55, baseSpDef = 65, baseSpeed = 45, bst = 505,
            type1 = "Fighting", type2 = "",
            ability1 = "Guts", ability2 = "Sheer Force", hiddenAbility = "Iron Fist",
            spriteUrl = sprite(534), spriteShinyUrl = shiny(534),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.4f, weight = 87.0f, flavorText = "Swings its concrete pillars with tremendous power."
        ),
        PokemonEntity(
            id = 549, name = "lilligant", displayName = "Lilligant",
            generation = 5, baseHp = 70, baseAtk = 60, baseDef = 75,
            baseSpAtk = 110, baseSpDef = 75, baseSpeed = 90, bst = 480,
            type1 = "Grass", type2 = "",
            ability1 = "Chlorophyll", ability2 = "Own Tempo", hiddenAbility = "Leaf Guard",
            spriteUrl = sprite(549), spriteShinyUrl = shiny(549),
            pokemmoTier = "NU", isLegendary = false, isMythical = false,
            height = 1.1f, weight = 16.3f, flavorText = "The fragrance of its petals is said to attract Pokémon from far away."
        ),
        PokemonEntity(
            id = 598, name = "ferrothorn", displayName = "Ferrothorn",
            generation = 5, baseHp = 74, baseAtk = 94, baseDef = 131,
            baseSpAtk = 54, baseSpDef = 116, baseSpeed = 20, bst = 489,
            type1 = "Grass", type2 = "Steel",
            ability1 = "Iron Barbs", ability2 = "", hiddenAbility = "Anticipation",
            spriteUrl = sprite(598), spriteShinyUrl = shiny(598),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.0f, weight = 110.0f, flavorText = "Moves by shooting spikes and using them to pierce the ceiling."
        ),
        PokemonEntity(
            id = 609, name = "chandelure", displayName = "Chandelure",
            generation = 5, baseHp = 60, baseAtk = 55, baseDef = 90,
            baseSpAtk = 145, baseSpDef = 90, baseSpeed = 80, bst = 520,
            type1 = "Ghost", type2 = "Fire",
            ability1 = "Flash Fire", ability2 = "Flame Body", hiddenAbility = "Infiltrator",
            spriteUrl = sprite(609), spriteShinyUrl = shiny(609),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.0f, weight = 34.3f, flavorText = "The flames it absorbs fuel its own ghostly flames."
        ),
        PokemonEntity(
            id = 612, name = "haxorus", displayName = "Haxorus",
            generation = 5, baseHp = 76, baseAtk = 147, baseDef = 90,
            baseSpAtk = 60, baseSpDef = 70, baseSpeed = 97, bst = 540,
            type1 = "Dragon", type2 = "",
            ability1 = "Rivalry", ability2 = "Mold Breaker", hiddenAbility = "Unnerve",
            spriteUrl = sprite(612), spriteShinyUrl = shiny(612),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.8f, weight = 105.5f, flavorText = "Its tusks are incredibly strong and can cut steel beams."
        ),
        PokemonEntity(
            id = 630, name = "mandibuzz", displayName = "Mandibuzz",
            generation = 5, baseHp = 110, baseAtk = 65, baseDef = 105,
            baseSpAtk = 55, baseSpDef = 95, baseSpeed = 80, bst = 510,
            type1 = "Dark", type2 = "Flying",
            ability1 = "Big Pecks", ability2 = "Overcoat", hiddenAbility = "Weak Armor",
            spriteUrl = sprite(630), spriteShinyUrl = shiny(630),
            pokemmoTier = "UU", isLegendary = false, isMythical = false,
            height = 1.2f, weight = 39.5f, flavorText = "Nests made from bones decorate its appearance."
        ),
        PokemonEntity(
            id = 635, name = "hydreigon", displayName = "Hydreigon",
            generation = 5, baseHp = 92, baseAtk = 105, baseDef = 90,
            baseSpAtk = 125, baseSpDef = 90, baseSpeed = 98, bst = 600,
            type1 = "Dark", type2 = "Dragon",
            ability1 = "Levitate", ability2 = "", hiddenAbility = "",
            spriteUrl = sprite(635), spriteShinyUrl = shiny(635),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.8f, weight = 160.0f, flavorText = "The three heads all consume food and devour anything they see."
        ),
        PokemonEntity(
            id = 637, name = "volcarona", displayName = "Volcarona",
            generation = 5, baseHp = 85, baseAtk = 60, baseDef = 65,
            baseSpAtk = 135, baseSpDef = 105, baseSpeed = 100, bst = 550,
            type1 = "Bug", type2 = "Fire",
            ability1 = "Flame Body", ability2 = "", hiddenAbility = "Swarm",
            spriteUrl = sprite(637), spriteShinyUrl = shiny(637),
            pokemmoTier = "OU", isLegendary = false, isMythical = false,
            height = 1.6f, weight = 46.0f, flavorText = "Legend says it was born from ancient flames and replaced the sun."
        ),
        PokemonEntity(
            id = 645, name = "landorus", displayName = "Landorus-T",
            generation = 5, baseHp = 89, baseAtk = 145, baseDef = 90,
            baseSpAtk = 105, baseSpDef = 80, baseSpeed = 91, bst = 600,
            type1 = "Ground", type2 = "Flying",
            ability1 = "Intimidate", ability2 = "", hiddenAbility = "Sand Force",
            spriteUrl = sprite(645), spriteShinyUrl = shiny(645),
            pokemmoTier = "OU", isLegendary = true, isMythical = false,
            height = 1.3f, weight = 68.0f, flavorText = "The energy that its third eye emits is said to create bountiful harvests."
        ),
    )
}
