package net.iessochoa.grupof.practicafinalandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
class Playlist(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val user: String,
    val name: String,
    val id_songs: List<Long>,
    )