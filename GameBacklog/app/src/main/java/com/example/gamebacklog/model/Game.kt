package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gameBacklogTable")
data class Game(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable
