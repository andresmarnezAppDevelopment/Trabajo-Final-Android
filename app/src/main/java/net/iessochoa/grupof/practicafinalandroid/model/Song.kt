package net.iessochoa.grupof.practicafinalandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
class Song(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val artist: String,
    val album: String,
    val year: String,
    ) {

}