package net.iessochoa.grupof.practicafinalandroid.repository

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.db.MixMuseDataBase
import net.iessochoa.grupof.practicafinalandroid.model.Login
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song

object PlaylistRepository {

    //
    private lateinit var modelDAO: PlaylistDAO
    private lateinit var application: Application
    private lateinit var playlists: List<Playlist>

    operator fun invoke(context: Context) {
        this.application = context.applicationContext as Application
        modelDAO = MixMuseDataBase.getDatabase(application).playlistDAO()
    }

    fun updatePlaylists(){
        playlists = modelDAO.getAllSongs();
    }

    fun updateSongsByUser(user:String){
        playlists = modelDAO.getAllSongsByUser(user)
    }

    fun getPlaylists() : List<Playlist>{
        return playlists
    }



}