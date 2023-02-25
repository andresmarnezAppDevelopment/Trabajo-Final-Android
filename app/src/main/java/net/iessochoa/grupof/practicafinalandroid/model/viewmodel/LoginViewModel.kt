package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Login
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.repository.LoginRepository
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository

class LoginViewModel(app: Application) : AndroidViewModel(app){
    private val repository : LoginRepository

    init {
        LoginRepository(getApplication<Application>().applicationContext)
        repository= LoginRepository
    }

    suspend fun checkLogin(username : String) : Login? {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLogin(username)
        }.join()

        return repository.getLogin()
    }
}