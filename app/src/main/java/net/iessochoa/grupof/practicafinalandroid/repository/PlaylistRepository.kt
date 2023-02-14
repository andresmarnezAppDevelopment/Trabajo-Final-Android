package net.iessochoa.grupof.practicafinalandroid.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class PlaylistRepository(private val playlistDAO: PlaylistDAO) {

    val allPlaylists: Flow<List<Playlist>> = playlistDAO.getAllSongs()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(playlist: Playlist) {
        playlistDAO.insert(playlist)
    }
}