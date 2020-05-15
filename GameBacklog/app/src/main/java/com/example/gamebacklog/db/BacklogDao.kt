package com.example.gamebacklog.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.Game

@Dao
interface BacklogDao {

    @Query("SELECT * FROM gameBacklogTable")
    fun getBacklog(): LiveData<List<Game>>

    @Query("DELETE FROM gameBacklogTable")
    fun deleteBacklog()

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)
}
