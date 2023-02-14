package net.iessochoa.grupof.practicafinalandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.model.*

@Database(entities = [(Login::class), (Playlist::class), (Song::class)], version = 1, exportSchema = false)
public abstract class MixMuseDataBase : RoomDatabase() {

    abstract fun loginDAO(): LoginDAO

    companion object {

        //Patron Singleton
        @Volatile
        private var SINGLETON: MixMuseDataBase? = null

        fun getDatabase(context : Context) : RoomDatabase {

            return SINGLETON?: synchronized(this) {
                val singleton = Room.databaseBuilder(
                    context.applicationContext,
                    MixMuseDataBase::class.java,
                    "mixmuse_db"
                ).build()

                SINGLETON = singleton
                singleton
            }
        }
    }
}