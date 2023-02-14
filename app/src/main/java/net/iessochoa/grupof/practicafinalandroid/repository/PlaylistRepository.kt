package net.iessochoa.grupof.practicafinalandroid.repository

import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class PlaylistRepository(private val playlistDAO: PlaylistDAO) {

    val allPlaylists: Flow<List<Playlist>> = playlistDAO.getAllSongs()

    /* By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
    */
}