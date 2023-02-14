package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel(){

    val allPlaylists: LiveData<List<Playlist>> = repository.allPlaylists.asLiveData()

    fun insert(playlist: Playlist) = viewModelScope.launch {
        repository.insert(playlist)
    }

    class PlaylistViewModelFactory(private val repository: PlaylistRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlaylistViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}