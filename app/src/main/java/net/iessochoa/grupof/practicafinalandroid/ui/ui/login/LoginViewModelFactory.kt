package net.iessochoa.grupof.practicafinalandroid.ui.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.iessochoa.grupof.practicafinalandroid.ui.data.LoginDataSource
import net.iessochoa.grupof.practicafinalandroid.ui.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}