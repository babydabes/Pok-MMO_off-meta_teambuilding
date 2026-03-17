package com.pokemmo.core.network.repository

import com.pokemmo.core.domain.model.StatSpread

/**
 * Parses a Pokémon Showdown team paste into partial team members.
 *
 * Expected format per Pokémon (blocks separated by blank lines):
 * ```
 * Nickname (Species) @ Item          — or just: Species @ Item
 * Ability: AbilityName
 * EVs: 252 HP / 252 Atk / 4 Spe
 * Jolly Nature
 * - Move 1
 * - Move 2
 * - Move 3
 * - Move 4
 * ```
 */
object ShowdownPasteParser {

    private val EV_REGEX = Regex("""(\d+)\s+(HP|Atk|Def|SpA|SpD|Spe)""")

    fun parse(paste: String): List<PartialMember> {
        val blocks = paste.trim().split(Regex("""\n\s*\n"""))
        return blocks.mapIndexedNotNull { slot, block ->
            runCatching { parseBlock(slot.coerceAtMost(5), block.trim()) }.getOrNull()
        }
    }

    private fun parseBlock(slot: Int, block: String): PartialMember {
        val lines = block.lines().map { it.trim() }
        if (lines.isEmpty()) error("Empty block")

        // Line 1: [Nickname (Species)] | Species  [@Item]
        val headerLine = lines[0]
        val (nickname, species, item) = parseHeader(headerLine)

        var ability = ""
        var nature = "Hardy"
        var evs = StatSpread()

        for (line in lines.drop(1)) {
            when {
                line.startsWith("Ability:") ->
                    ability = line.removePrefix("Ability:").trim()
                line.startsWith("EVs:") ->
                    evs = parseEvs(line.removePrefix("EVs:").trim())
                line.endsWith("Nature") ->
                    nature = line.removeSuffix("Nature").trim()
            }
        }

        // Resolve species to pokemonId — for now use a best-effort name lookup.
        // The actual resolution happens in TeamRepositoryImpl using pokemonDao.
        val pokemonId = speciesNameToId(species)

        return PartialMember(
            slot = slot,
            pokemonId = pokemonId,
            nickname = nickname,
            nature = nature,
            ability = ability,
            item = item,
            evs = evs,
        )
    }

    private fun parseHeader(line: String): Triple<String, String, String> {
        val atParts = line.split("@", limit = 2)
        val namePart = atParts[0].trim()
        val item = if (atParts.size > 1) atParts[1].trim() else ""

        val parenMatch = Regex("""^(.+?)\s*\((.+?)\)$""").find(namePart)
        return if (parenMatch != null) {
            Triple(parenMatch.groupValues[1].trim(), parenMatch.groupValues[2].trim(), item)
        } else {
            Triple("", namePart, item)
        }
    }

    private fun parseEvs(evString: String): StatSpread {
        var hp = 0; var atk = 0; var def = 0; var spa = 0; var spd = 0; var spe = 0
        EV_REGEX.findAll(evString).forEach { match ->
            val value = match.groupValues[1].toInt()
            when (match.groupValues[2]) {
                "HP"  -> hp = value
                "Atk" -> atk = value
                "Def" -> def = value
                "SpA" -> spa = value
                "SpD" -> spd = value
                "Spe" -> spe = value
            }
        }
        return StatSpread(hp, atk, def, spa, spd, spe)
    }

    /** Simple name→id mapping for common Pokémon. Full resolution uses the DB. */
    private fun speciesNameToId(name: String): Int {
        val lookup = mapOf(
            "charizard" to 6, "pikachu" to 25, "arcanine" to 59,
            "gyarados" to 130, "dragonite" to 149, "feraligatr" to 160,
            "umbreon" to 197, "scizor" to 212, "heracross" to 214,
            "suicune" to 245, "tyranitar" to 248, "swampert" to 260,
            "gardevoir" to 282, "flygon" to 330, "altaria" to 334,
            "salamence" to 373, "metagross" to 376, "empoleon" to 395,
            "luxray" to 405, "roserade" to 407, "garchomp" to 445,
            "lucario" to 448, "abomasnow" to 460, "weavile" to 461,
            "magnezone" to 462, "gliscor" to 472, "rotom-w" to 479,
            "excadrill" to 530, "conkeldurr" to 534, "lilligant" to 549,
            "ferrothorn" to 598, "chandelure" to 609, "haxorus" to 612,
            "mandibuzz" to 630, "hydreigon" to 635, "volcarona" to 637,
            "landorus-t" to 645, "landorus" to 645,
        )
        return lookup[name.lowercase().trim()] ?: 0
    }
}
