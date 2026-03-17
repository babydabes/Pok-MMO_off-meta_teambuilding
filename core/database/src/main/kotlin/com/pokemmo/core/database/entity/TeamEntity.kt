package com.pokemmo.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val format: String,         // "PokéMMO-OU", "PokéMMO-UU", "PokéMMO-NU"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val notes: String = "",
    val isArchived: Boolean = false
)

@Entity(
    tableName = "team_member",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("teamId")]
)
data class TeamMemberEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val teamId: Long,
    val pokemonId: Int,
    val slot: Int,              // 0–5
    val nickname: String = "",
    val level: Int = 100,
    val itemName: String = "",
    val abilityName: String = "",
    val natureName: String = "Hardy",
    val move1Id: Int = 0,       // 0 = empty
    val move2Id: Int = 0,
    val move3Id: Int = 0,
    val move4Id: Int = 0,
    val evHp: Int = 0,
    val evAtk: Int = 0,
    val evDef: Int = 0,
    val evSpAtk: Int = 0,
    val evSpDef: Int = 0,
    val evSpeed: Int = 0,
    val ivHp: Int = 31,
    val ivAtk: Int = 31,
    val ivDef: Int = 31,
    val ivSpAtk: Int = 31,
    val ivSpDef: Int = 31,
    val ivSpeed: Int = 31,
    val isShiny: Boolean = false
)

data class TeamWithMembers(
    @Embedded val team: TeamEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val members: List<TeamMemberEntity>
)
