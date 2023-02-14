package net.iessochoa.grupof.practicafinalandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logins")
class Login(
    @PrimaryKey val user: String,
    val password: String) {
}