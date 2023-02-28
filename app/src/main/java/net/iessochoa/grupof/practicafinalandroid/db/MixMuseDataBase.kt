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
                        loadUsers(database.loginDAO())
                        loadSongs(database.songDAO())
                        loadPlaylists(database.playlistDAO())
                    }
                }
            }

            fun loadUsers(loginDAO: LoginDAO){
                loginDAO.insert(Login("admin","admin"))
            }

            suspend fun loadPlaylists(playlistDAO: PlaylistDAO){
                val testList : List<Long> = listOf<Long>(1L, 2L)
                playlistDAO.insert(Playlist(0,"admin","My Favourites Songs" , testList))
                playlistDAO.insert(Playlist(1,"admin","Rock and Roll" , testList))
                playlistDAO.insert(Playlist(2,"admin","Chillin'" , testList))
                playlistDAO.insert(Playlist(3,"admin","Test" , testList))

            }

            suspend fun loadSongs(songDAO: SongDAO){
                songDAO.insert(Song(0,"Sin Señal","Quevedo" , "Donde quiero estar", "2023", "https://estaticos-cdn.epe.es/clip/9a789b18-990c-446c-89da-d1d370c4bd7b_alta-libre-aspect-ratio_default_0.jpg   "))
                songDAO.insert(Song(1,"Ingobernable","C. Tangana" , "El Madrileño", "2021", "http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcTtEJmIWG1-9_6ILE8LqWgircjwRLTMGyC6KLJGFnrXE0-1yi2Ffvgh_ETArnfuigJ8"))
                songDAO.insert(Song(2,"Los Tontos","C. Tangana" , "El Madrileño", "2021", "http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcTtEJmIWG1-9_6ILE8LqWgircjwRLTMGyC6KLJGFnrXE0-1yi2Ffvgh_ETArnfuigJ8"))
                songDAO.insert(Song(3,"The Song Is Over","The Who" , "Who's Next", "1971", "https://ep00.epimg.net/elpais/imagenes/2016/06/16/album/1466092250_633396_1466093451_album_normal.jpg"))
                songDAO.insert(Song(4,"Behind Blue Eyes","The Who" , "Who's Next", "1971", "https://ep00.epimg.net/elpais/imagenes/2016/06/16/album/1466092250_633396_1466093451_album_normal.jpg"))
                songDAO.insert(Song(5,"Mercy Mercy","Rolling Stones" , "Out of Our Heads", "1965", "https://cdn.britannica.com/41/197341-050-4859B808/The-Rolling-Stones-Bill-Wyman-Keith-Richards-1964.jpg"))
                songDAO.insert(Song(6,"Ooh Yeah","Sophie and the Giants" , "Last Night", "2007", "https://conciertos.club/doc/a/2021/a_sophieandthegiants.jpg"))
                songDAO.insert(Song(7,"The Stars","Sophie and the Giants" , "Last Night", "2007", "https://conciertos.club/doc/a/2021/a_sophieandthegiants.jpg"))
                songDAO.insert(Song(8,"Hold The Line","Avicii" , "Tim", "2019","http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcQFVY14SO3Oar2PfA-n3OmLYCGJMtADI1pLDtqAH16eEqGOUZkxZ8vsmQJxIqbZKJVS"))
                songDAO.insert(Song(9,"Zero","Image Dragons" , "Origins", "2018", "https://cd1.taquilla.com/data/images/t/c2/imagine-dragons.jpg"))
                songDAO.insert(Song(10,"Birds","Image Dragons" , "Origins", "2018", "https://cd1.taquilla.com/data/images/t/c2/imagine-dragons.jpg"))
                songDAO.insert(Song(11,"Lose Somebody","OneRepublic" , "Human", "2020", "https://i.scdn.co/image/ab6761610000e5eb77bf00f67e21f514dc44c485"))
                songDAO.insert(Song(12,"Forgot About You","OneRepublic" , "Human", "2020", "https://i.scdn.co/image/ab6761610000e5eb77bf00f67e21f514dc44c485"))
            }

        }


    }
}