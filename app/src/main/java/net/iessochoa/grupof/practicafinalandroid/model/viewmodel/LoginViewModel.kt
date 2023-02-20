package net.iessochoa.grupof.practicafinalandroid.model.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.repository.LoginRepository
import net.iessochoa.grupof.practicafinalandroid.repository.PlaylistRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel(){

    fun insert(user: String, password: String) = viewModelScope.launch {
        repository.loginUser(user, password)
    }

    class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModelFactory::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModelFactory(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}