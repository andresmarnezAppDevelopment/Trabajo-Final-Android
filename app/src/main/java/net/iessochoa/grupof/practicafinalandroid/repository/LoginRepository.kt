package net.iessochoa.grupof.practicafinalandroid.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class LoginRepository (private val loginDAO: LoginDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loginUser(user: String, password: String) {

        //IMPLEMENTCHECKS
    }
}