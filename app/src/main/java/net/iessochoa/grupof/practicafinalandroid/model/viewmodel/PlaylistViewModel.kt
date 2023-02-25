package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.repository.LoginRepository
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository

class PlaylistViewModel(app: Application) : AndroidViewModel(app){
    private val playlistRepository: PlaylistRepository
    //TODO: Comentado porque explota
    //val allPlaylists: LiveData<List<Playlist>> = playlistRepository.allPlaylists.asLiveData()

    init {
        PlaylistRepository(getApplication<Application>().applicationContext)
        playlistRepository = PlaylistRepository
    }

    fun insert(playlist: Playlist) = viewModelScope.launch {
        playlistRepository.insert(playlist)
    }
}