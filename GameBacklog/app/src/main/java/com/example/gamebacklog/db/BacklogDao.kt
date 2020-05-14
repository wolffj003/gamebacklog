package com.example.gamebacklog.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gamebacklog.model.Game

@Dao
interface BacklogDao {

    @Query("SELECT * FROM gameBacklogTable")
    fun getBacklog(): LiveData<Game?>

    @Query("DELETE FROM gameBacklogTable")
    fun deleteBacklog()

    @Update
    suspend fun updateBacklog(game: Game)

    @Insert
    suspend fun insertBacklog(game: Game)  // Logischere naam insertGame?
}
