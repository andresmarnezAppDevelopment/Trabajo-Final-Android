package net.iessochoa.grupof.practicafinalandroid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "songs")
@Parcelize
class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val artist: String,
    val album: String,
    val year: String,
    val photo: String,
    ): Parcelable {
    constructor (name: String, artist: String, album: String, year: String, photo: String):
            this(null ,name, artist, album, year, photo)
}