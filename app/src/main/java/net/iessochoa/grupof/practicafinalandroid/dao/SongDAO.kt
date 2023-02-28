package net.iessochoa.grupof.practicafinalandroid.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.model.Song

@Dao
interface SongDAO {
    @Query("SELECT * FROM songs ORDER BY name ASC")
    fun getAllSongs(): List<Song>

    @Query("SELECT * FROM songs WHERE id = :id")
    fun getSongById(id: Long): Song

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song: Song)

    @Query("DELETE FROM songs")
    fun deleteAll()

    @Query("DELETE FROM songs WHERE id = :id")
    suspend fun deleteById(id: Long?)
}