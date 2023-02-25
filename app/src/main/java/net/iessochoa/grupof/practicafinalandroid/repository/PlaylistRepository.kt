package net.iessochoa.grupof.practicafinalandroid.repository

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.db.MixMuseDataBase
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

object PlaylistRepository {
    private lateinit var application: Application
    private lateinit var playlistDAO: PlaylistDAO
    //TODO: Cometnado porque da problemas
    //val allPlaylists: Flow<List<Playlist>> = playlistDAO.getAllSongs()

    operator fun invoke(context: Context) {
        this.application = context.applicationContext as Application
        playlistDAO = MixMuseDataBase.getDatabase(application).playlistDAO()
    }

    //TODO: Esto lo he quitado porque daba problemas
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(playlist: Playlist) {
        //playlistDAO.insert(playlist)
    }
}