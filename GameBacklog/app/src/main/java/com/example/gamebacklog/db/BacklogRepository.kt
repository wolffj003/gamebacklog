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

    fun getBacklog(): LiveData<Game?> {
        return backlogDao.getBacklog()
    }

    suspend fun updateBacklog(game: Game) {
        backlogDao.updateBacklog(game)
    }
}
