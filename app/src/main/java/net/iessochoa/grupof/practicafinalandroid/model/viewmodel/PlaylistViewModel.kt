package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Login
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.repository.LoginRepository
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository

class PlaylistViewModel(app: Application) : AndroidViewModel(app){

        private val repository : PlaylistRepository

        init {
            PlaylistRepository(getApplication<Application>().applicationContext)
            repository= PlaylistRepository
        }

        suspend fun getAllPlaylists(username : String) : List<Playlist> {

            viewModelScope.launch(Dispatchers.IO) {
                repository.updateSongsByUser(username)
            }.join()

            return repository.getPlaylists()

        }
}