package net.iessochoa.grupof.practicafinalandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.dao.LoginDAO
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.dao.SongDAO
import net.iessochoa.grupof.practicafinalandroid.model.*

@Database(entities = [(Login::class), (Playlist::class), (Song::class)], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
public abstract class MixMuseDataBase : RoomDatabase() {

    abstract fun loginDAO(): LoginDAO
    abstract fun playlistDAO(): PlaylistDAO
    abstract fun songDAO(): SongDAO

    companion object {

        //Patron Singleton
        @Volatile
        private var SINGLETON: MixMuseDataBase? = null

        fun getDatabase(context : Context) : MixMuseDataBase {

            return SINGLETON?: synchronized(this) {
                val singleton = Room.databaseBuilder(
                    context.applicationContext,
                    MixMuseDataBase::class.java,
                    "mixmuse_db"
                ).addCallback(inicioDB()).build()

                SINGLETON = singleton
                singleton
            }
        }

        private class inicioDB() : RoomDatabase.Callback(){

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                SINGLETON?.let {
                    database -> GlobalScope.launch {
                        cargarUsers(database.loginDAO())
                    }
                }
            }

            suspend fun cargarUsers(loginDAO: LoginDAO){

                loginDAO.insert(Login("admin","admin"))

            }

        }


    }
}