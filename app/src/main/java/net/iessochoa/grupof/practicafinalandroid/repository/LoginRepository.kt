package net.iessochoa.grupof.practicafinalandroid.repository

import android.app.Application
import android.content.Context
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.db.MixMuseDataBase
import net.iessochoa.grupof.practicafinalandroid.model.Login
import kotlin.math.log

object LoginRepository {
    private lateinit var modelDAO: LoginDAO
    private lateinit var application: Application
    private var login: Login? = null

    operator fun invoke(context: Context) {

        this.application = context.applicationContext as Application
        modelDAO = MixMuseDataBase.getDatabase(application).loginDAO()
    }

    fun getLogin(name : String) : Login? {
        login = modelDAO.getLogin(name)
        return login
    }

    fun getLogin() = this.login
}