package net.iessochoa.grupof.practicafinalandroid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "playlists")
@Parcelize
class Playlist(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val user: String,
    val name: String,
    val id_songs: List<Long>,
    ) : Parcelable {
    constructor (user: String, name: String, id_songs: List<Long>):
            this(null, user, name, id_songs)
    }