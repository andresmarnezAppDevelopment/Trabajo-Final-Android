package net.iessochoa.grupof.practicafinalandroid.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

@Dao
interface PlaylistDAO {

    @Query("SELECT * FROM playlists ORDER BY user ASC")
    fun getAllSongs(): Flow<List<Playlist>>

    @Query("SELECT * FROM playlists WHERE user = :user")
    fun getAllSongsByUser(user: String): Flow<List<Playlist>>

    @Query("SELECT songs FROM playlists WHERE id = :id")
    suspend fun getSongsById(id : Float)

    @Query("DELETE FROM playlists")
    suspend fun deleteAll()

}