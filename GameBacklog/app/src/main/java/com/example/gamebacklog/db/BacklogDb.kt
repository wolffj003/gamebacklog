package com.example.gamebacklog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamebacklog.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BacklogDb : RoomDatabase() {

    abstract fun backlogDao(): BacklogDao

    companion object {
        private const val DATABASE_NAME = "BACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: BacklogDb? = null

        fun getDatabase(context: Context): BacklogDb? {
            if (INSTANCE == null) {
                synchronized(BacklogDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BacklogDb::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
