package net.iessochoa.grupof.practicafinalandroid.repository

import android.app.Application
import android.content.Context
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.dao.SongDAO
import net.iessochoa.grupof.practicafinalandroid.db.MixMuseDataBase
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song

object SongRepository {
    private lateinit var modelDAO: SongDAO
    private lateinit var application: Application
    private lateinit var songs: List<Song>

    operator fun invoke(context: Context) {
        this.application = context.applicationContext as Application
        modelDAO = MixMuseDataBase.getDatabase(application).songDAO()
    }

    fun getSongs() : List<Song>{
        return songs
    }

    suspend fun deleteById(id: Long?){
        modelDAO.deleteById(id)
    }
}