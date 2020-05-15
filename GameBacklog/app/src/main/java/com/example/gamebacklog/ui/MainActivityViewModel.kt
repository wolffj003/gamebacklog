package com.example.gamebacklog.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.db.BacklogRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val backlogRepository = BacklogRepository(application.applicationContext)

    val backlog: LiveData<List<Game>> = backlogRepository.getBacklog()

    fun insertGame(game: Game) {
        ioScope.launch { backlogRepository.insertGame(game) }
    }

    fun deleteGame(game: Game) {
        ioScope.launch { backlogRepository.deleteGame(game) }
    }

    fun deleteBacklog() {
        ioScope.launch { backlogRepository.deleteBacklog() }
    }
}