package com.example.gamebacklog.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game

class BacklogRepository(context: Context) {

    private val backlogDao: BacklogDao

    init {
        val database = BacklogDb.getDatabase(context)
        backlogDao = database!!.backlogDao()
    }

    fun getBacklog(): LiveData<List<Game>> {
        return backlogDao.getBacklog()
    }

    fun deleteBacklog() {
        return backlogDao.deleteBacklog()
    }

    suspend fun insertGame(game: Game) {
        backlogDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        backlogDao.deleteGame(game)
    }
}
