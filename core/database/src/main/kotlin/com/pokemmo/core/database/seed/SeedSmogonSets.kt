package com.pokemmo.core.database.seed

import com.pokemmo.core.database.entity.SmogonSetEntity

object SeedSmogonSets {

    val sets: List<SmogonSetEntity> = listOf(

        // Ferrothorn
        SmogonSetEntity(
            pokemonId = 598, setName = "Spikes Lead", tier = "OU",
            nature = "Relaxed", abilityName = "Iron Barbs", itemName = "Leftovers",
            move1 = "Spikes", move2 = "Stealth Rock", move3 = "Leech Seed", move4 = "Power Whip",
            evHp = 252, evAtk = 0, evDef = 88, evSpAtk = 0, evSpDef = 168, evSpeed = 0,
            description = "Hazard-stacking Ferrothorn. Cripples fast teams with passive damage.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 598, setName = "Rocky Helmet", tier = "OU",
            nature = "Sassy", abilityName = "Iron Barbs", itemName = "Rocky Helmet",
            move1 = "Gyro Ball", move2 = "Power Whip", move3 = "Leech Seed", move4 = "Stealth Rock",
            evHp = 252, evAtk = 0, evDef = 88, evSpAtk = 0, evSpDef = 168, evSpeed = 0,
            description = "Rocky Helmet punishes contact moves alongside Iron Barbs.",
            usageRank = 2
        ),

        // Rotom-W
        SmogonSetEntity(
            pokemonId = 479, setName = "Offensive Pivot", tier = "OU",
            nature = "Timid", abilityName = "Levitate", itemName = "Choice Scarf",
            move1 = "Volt Switch", move2 = "Hydro Pump", move3 = "Thunderbolt", move4 = "Trick",
            evHp = 4, evAtk = 0, evDef = 0, evSpAtk = 252, evSpDef = 0, evSpeed = 252,
            description = "Fast revenge killer that maintains momentum with Volt Switch.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 479, setName = "Bulky Attacker", tier = "OU",
            nature = "Modest", abilityName = "Levitate", itemName = "Leftovers",
            move1 = "Volt Switch", move2 = "Hydro Pump", move3 = "Will-O-Wisp", move4 = "Pain Split",
            evHp = 248, evAtk = 0, evDef = 28, evSpAtk = 232, evSpDef = 0, evSpeed = 0,
            description = "Bulky pivot that cripples physical attackers with Will-O-Wisp.",
            usageRank = 2
        ),

        // Tyranitar
        SmogonSetEntity(
            pokemonId = 248, setName = "Choice Band", tier = "OU",
            nature = "Adamant", abilityName = "Sand Stream", itemName = "Choice Band",
            move1 = "Crunch", move2 = "Stone Edge", move3 = "Pursuit", move4 = "Superpower",
            evHp = 4, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Raw power. Pursuit traps Psychics and Ghosts.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 248, setName = "Stealth Rock Lead", tier = "OU",
            nature = "Jolly", abilityName = "Sand Stream", itemName = "Leftovers",
            move1 = "Stealth Rock", move2 = "Crunch", move3 = "Stone Edge", move4 = "Pursuit",
            evHp = 252, evAtk = 40, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 216,
            description = "Dedicated lead that sets Stealth Rock and keeps up weather.",
            usageRank = 2
        ),

        // Garchomp
        SmogonSetEntity(
            pokemonId = 445, setName = "Choice Scarf", tier = "OU",
            nature = "Jolly", abilityName = "Rough Skin", itemName = "Choice Scarf",
            move1 = "Outrage", move2 = "Earthquake", move3 = "Stone Edge", move4 = "Dragon Claw",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Fastest Dragon in OU. Cleans late-game after checks are worn down.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 445, setName = "Swords Dance", tier = "OU",
            nature = "Jolly", abilityName = "Rough Skin", itemName = "Salac Berry",
            move1 = "Swords Dance", move2 = "Earthquake", move3 = "Dragon Claw", move4 = "Fire Fang",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Sweeper set. Salac Berry lets it outspeed everything at +1 Swords Dance.",
            usageRank = 2
        ),

        // Volcarona
        SmogonSetEntity(
            pokemonId = 637, setName = "Quiver Dance Sweeper", tier = "OU",
            nature = "Timid", abilityName = "Flame Body", itemName = "Lum Berry",
            move1 = "Quiver Dance", move2 = "Bug Buzz", move3 = "Fire Blast", move4 = "Giga Drain",
            evHp = 4, evAtk = 0, evDef = 0, evSpAtk = 252, evSpDef = 0, evSpeed = 252,
            description = "The premier win-condition of BW OU. One Quiver Dance often wins games.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 637, setName = "SubQuiver", tier = "OU",
            nature = "Timid", abilityName = "Flame Body", itemName = "Life Orb",
            move1 = "Quiver Dance", move2 = "Bug Buzz", move3 = "Fire Blast", move4 = "Substitute",
            evHp = 24, evAtk = 0, evDef = 0, evSpAtk = 232, evSpDef = 0, evSpeed = 252,
            description = "Substitute shields from priority and status while setting up.",
            usageRank = 2
        ),

        // Landorus-T
        SmogonSetEntity(
            pokemonId = 645, setName = "Bulky Pivot", tier = "OU",
            nature = "Jolly", abilityName = "Intimidate", itemName = "Rocky Helmet",
            move1 = "Stealth Rock", move2 = "Earthquake", move3 = "Stone Edge", move4 = "U-turn",
            evHp = 252, evAtk = 80, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 176,
            description = "The best physical wall and pivot in BW. Intimidate cripples physical attackers.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 645, setName = "Choice Scarf", tier = "OU",
            nature = "Jolly", abilityName = "Intimidate", itemName = "Choice Scarf",
            move1 = "Earthquake", move2 = "Stone Edge", move3 = "U-turn", move4 = "Superpower",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Revenge killer that comes in on weakened foes and fires off powerful moves.",
            usageRank = 2
        ),

        // Scizor
        SmogonSetEntity(
            pokemonId = 212, setName = "Choice Band", tier = "OU",
            nature = "Adamant", abilityName = "Technician", itemName = "Choice Band",
            move1 = "Bullet Punch", move2 = "U-turn", move3 = "Superpower", move4 = "Pursuit",
            evHp = 248, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 8, evSpeed = 0,
            description = "Powerful priority attacker. Bullet Punch at +1 Technician is devastating.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 212, setName = "Swords Dance", tier = "OU",
            nature = "Adamant", abilityName = "Technician", itemName = "Life Orb",
            move1 = "Swords Dance", move2 = "Bullet Punch", move3 = "X-Scissor", move4 = "Superpower",
            evHp = 32, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 224,
            description = "After one Swords Dance, Bullet Punch picks off faster threats.",
            usageRank = 2
        ),

        // Excadrill
        SmogonSetEntity(
            pokemonId = 530, setName = "Sand Rush Sweeper", tier = "OU",
            nature = "Adamant", abilityName = "Sand Rush", itemName = "Air Balloon",
            move1 = "Earthquake", move2 = "Iron Head", move3 = "Rock Slide", move4 = "Swords Dance",
            evHp = 4, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Under sand doubles Speed. Swords Dance makes it nearly unstoppable.",
            usageRank = 1
        ),

        // Salamence
        SmogonSetEntity(
            pokemonId = 373, setName = "Mixed Lure", tier = "OU",
            nature = "Naive", abilityName = "Moxie", itemName = "Life Orb",
            move1 = "Draco Meteor", move2 = "Outrage", move3 = "Earthquake", move4 = "Fire Blast",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 88, evSpDef = 4, evSpeed = 164,
            description = "Mixed attacker that lures and eliminates Steel-type checks to Dragon moves.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 373, setName = "Dragon Dance", tier = "OU",
            nature = "Jolly", abilityName = "Moxie", itemName = "Lum Berry",
            move1 = "Dragon Dance", move2 = "Outrage", move3 = "Earthquake", move4 = "Stone Edge",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "After one Dragon Dance and a KO, Moxie snowballs into an unstoppable sweep.",
            usageRank = 2
        ),

        // Dragonite
        SmogonSetEntity(
            pokemonId = 149, setName = "Multiscale Mixed", tier = "OU",
            nature = "Hasty", abilityName = "Multiscale", itemName = "Life Orb",
            move1 = "Draco Meteor", move2 = "Hurricane", move3 = "Extremespeed", move4 = "Earthquake",
            evHp = 4, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Multiscale ensures it takes a hit while ExtremeSpeed provides priority.",
            usageRank = 1
        ),
        SmogonSetEntity(
            pokemonId = 149, setName = "Dragon Dance", tier = "OU",
            nature = "Adamant", abilityName = "Multiscale", itemName = "Lum Berry",
            move1 = "Dragon Dance", move2 = "Outrage", move3 = "Earthquake", move4 = "Fire Punch",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Lum Berry cures confusion from Outrage. One Dragon Dance usually wins.",
            usageRank = 2
        ),

        // Lucario
        SmogonSetEntity(
            pokemonId = 448, setName = "Swords Dance", tier = "OU",
            nature = "Adamant", abilityName = "Justified", itemName = "Life Orb",
            move1 = "Swords Dance", move2 = "Close Combat", move3 = "Bullet Punch", move4 = "Ice Punch",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Deadly Swords Dance sweeper with priority Bullet Punch for revenge killing.",
            usageRank = 1
        ),

        // Hydreigon
        SmogonSetEntity(
            pokemonId = 635, setName = "Choice Specs", tier = "OU",
            nature = "Timid", abilityName = "Levitate", itemName = "Choice Specs",
            move1 = "Draco Meteor", move2 = "Dark Pulse", move3 = "Fire Blast", move4 = "U-turn",
            evHp = 4, evAtk = 0, evDef = 0, evSpAtk = 252, evSpDef = 0, evSpeed = 252,
            description = "Massive special power. Draco Meteor nuke + U-turn for momentum.",
            usageRank = 1
        ),

        // Heracross
        SmogonSetEntity(
            pokemonId = 214, setName = "Choice Scarf Guts", tier = "OU",
            nature = "Jolly", abilityName = "Guts", itemName = "Choice Scarf",
            move1 = "Close Combat", move2 = "Megahorn", move3 = "Earthquake", move4 = "Stone Edge",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Guts turns status into extra power. Scarf makes it a dangerous revenge killer.",
            usageRank = 1
        ),

        // Weavile
        SmogonSetEntity(
            pokemonId = 461, setName = "Choice Band", tier = "OU",
            nature = "Jolly", abilityName = "Pressure", itemName = "Choice Band",
            move1 = "Pursuit", move2 = "Night Slash", move3 = "Ice Punch", move4 = "Low Kick",
            evHp = 0, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 4, evSpeed = 252,
            description = "Fastest Pursuit trapper in OU. Catches fleeing Psychic and Ghost types.",
            usageRank = 1
        ),

        // Magnezone
        SmogonSetEntity(
            pokemonId = 462, setName = "Specs Trapper", tier = "OU",
            nature = "Modest", abilityName = "Magnet Pull", itemName = "Choice Specs",
            move1 = "Thunderbolt", move2 = "Flash Cannon", move3 = "Hidden Power (Fire)", move4 = "Volt Switch",
            evHp = 4, evAtk = 0, evDef = 0, evSpAtk = 252, evSpDef = 0, evSpeed = 252,
            description = "Magnet Pull traps and eliminates Ferrothorn and Skarmory.",
            usageRank = 1
        ),

        // Gliscor
        SmogonSetEntity(
            pokemonId = 472, setName = "Poison Heal Tank", tier = "OU",
            nature = "Impish", abilityName = "Poison Heal", itemName = "Toxic Orb",
            move1 = "Earthquake", move2 = "Ice Fang", move3 = "U-turn", move4 = "Stealth Rock",
            evHp = 252, evAtk = 0, evDef = 184, evSpAtk = 0, evSpDef = 72, evSpeed = 0,
            description = "Toxic Orb + Poison Heal gives recovery, status immunity, and 12.5% HP/turn.",
            usageRank = 1
        ),

        // Chandelure
        SmogonSetEntity(
            pokemonId = 609, setName = "Choice Specs", tier = "OU",
            nature = "Timid", abilityName = "Shadow Tag", itemName = "Choice Specs",
            move1 = "Shadow Ball", move2 = "Fire Blast", move3 = "Energy Ball", move4 = "Trick",
            evHp = 0, evAtk = 0, evDef = 0, evSpAtk = 252, evSpDef = 4, evSpeed = 252,
            description = "Shadow Tag traps and destroys Normal-immune users. Strongest Ghost special attacker.",
            usageRank = 1
        ),

        // Metagross
        SmogonSetEntity(
            pokemonId = 376, setName = "Choice Band", tier = "OU",
            nature = "Adamant", abilityName = "Clear Body", itemName = "Choice Band",
            move1 = "Meteor Mash", move2 = "Earthquake", move3 = "Ice Punch", move4 = "Bullet Punch",
            evHp = 4, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Raw power with great typing. Bullet Punch for priority.",
            usageRank = 1
        ),

        // Conkeldurr
        SmogonSetEntity(
            pokemonId = 534, setName = "Guts Wallbreaker", tier = "OU",
            nature = "Adamant", abilityName = "Guts", itemName = "Flame Orb",
            move1 = "Close Combat", move2 = "Ice Punch", move3 = "Mach Punch", move4 = "Drain Punch",
            evHp = 120, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 136, evSpeed = 0,
            description = "Flame Orb activates Guts for massive power. Mach Punch provides priority.",
            usageRank = 1
        ),

        // Haxorus
        SmogonSetEntity(
            pokemonId = 612, setName = "Dragon Dance", tier = "OU",
            nature = "Jolly", abilityName = "Mold Breaker", itemName = "Lum Berry",
            move1 = "Dragon Dance", move2 = "Outrage", move3 = "Earthquake", move4 = "Rock Slide",
            evHp = 4, evAtk = 252, evDef = 0, evSpAtk = 0, evSpDef = 0, evSpeed = 252,
            description = "Highest base Attack Dragon in BW. Mold Breaker bypasses Levitate.",
            usageRank = 1
        ),
    )
}
