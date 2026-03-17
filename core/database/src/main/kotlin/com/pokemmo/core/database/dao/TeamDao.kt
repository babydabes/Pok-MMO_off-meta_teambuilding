package com.pokemmo.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.pokemmo.core.database.entity.TeamEntity
import com.pokemmo.core.database.entity.TeamMemberEntity
import com.pokemmo.core.database.entity.TeamWithMembers
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team WHERE isArchived = 0 ORDER BY updatedAt DESC")
    fun observeActiveTeams(): Flow<List<TeamEntity>>

    @Transaction
    @Query("SELECT * FROM team WHERE id = :teamId")
    fun observeTeamWithMembers(teamId: Long): Flow<TeamWithMembers?>

    @Query("SELECT * FROM team WHERE id = :teamId")
    suspend fun getTeamById(teamId: Long): TeamEntity?

    @Query("SELECT * FROM team_member WHERE teamId = :teamId ORDER BY slot ASC")
    suspend fun getMembersForTeam(teamId: Long): List<TeamMemberEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity): Long

    @Update
    suspend fun updateTeam(team: TeamEntity)

    @Query("UPDATE team SET isArchived = 1, updatedAt = :now WHERE id = :teamId")
    suspend fun archiveTeam(teamId: Long, now: Long = System.currentTimeMillis())

    @Query("DELETE FROM team WHERE id = :teamId")
    suspend fun deleteTeam(teamId: Long)

    @Upsert
    suspend fun upsertMember(member: TeamMemberEntity): Long

    @Query("DELETE FROM team_member WHERE teamId = :teamId AND slot = :slot")
    suspend fun removeMemberBySlot(teamId: Long, slot: Int)

    @Query("DELETE FROM team_member WHERE teamId = :teamId")
    suspend fun clearAllMembers(teamId: Long)

    @Query("UPDATE team SET updatedAt = :now WHERE id = :teamId")
    suspend fun touchTeam(teamId: Long, now: Long = System.currentTimeMillis())
}
