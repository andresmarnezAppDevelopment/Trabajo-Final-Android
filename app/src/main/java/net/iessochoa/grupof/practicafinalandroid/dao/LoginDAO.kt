package net.iessochoa.grupof.practicafinalandroid.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iessochoa.grupof.practicafinalandroid.model.Login

@Dao
interface LoginDAO {
    @Query("SELECT * FROM logins ORDER BY user ASC")
    suspend fun getAllLogs(): Flow<List<Login>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(login: Login)

    @Query("DELETE FROM logins")
    suspend fun deleteAll()

    @Query("SELECT * FROM logins WHERE user = :user")
    fun getLogin(user: String): Login
}