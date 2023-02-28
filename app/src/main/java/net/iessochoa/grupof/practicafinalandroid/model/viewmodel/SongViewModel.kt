package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository
import net.iessochoa.grupof.practicafinalandroid.repository.SongRepository

class SongViewModel (app: Application) : AndroidViewModel(app) {

    private val repository: SongRepository

    init {
        SongRepository(getApplication<Application>().applicationContext)
        repository = SongRepository
    }

    suspend fun getAllSongs(id_songs: List<Long>): List<Song> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSongs(id_songs)
        }.join()

        return repository.getSongs()
    }
/*
    suspend fun getAllSongsById(id_songs: List<Long>): List<Song> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSongs(id_songs)
        }.join()

        return repository.getSongs()
    }

 */



    suspend fun getSongsById(username: String): List<Song> {
       /*
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSongsByUser(username)
        }.join()
*/
        return repository.getSongs()
    }

    suspend fun deleteById(id: Long?){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteById(id)
        }.join()
    }
}