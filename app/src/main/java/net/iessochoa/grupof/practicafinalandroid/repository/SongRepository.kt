package net.iessochoa.grupof.practicafinalandroid.repository

import android.app.Application
import android.content.Context
import net.iessochoa.grupof.practicafinalandroid.dao.PlaylistDAO
import net.iessochoa.grupof.practicafinalandroid.dao.SongDAO
import net.iessochoa.grupof.practicafinalandroid.db.MixMuseDataBase
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song

object SongRepository {
    private lateinit var modelDAO: SongDAO
    private lateinit var application: Application
    private lateinit var songs: List<Song>
    //private lateinit var songs_aux: ArrayList<Song>
    private lateinit var song: Song

    operator fun invoke(context: Context) {
        this.application = context.applicationContext as Application
        modelDAO = MixMuseDataBase.getDatabase(application).songDAO()
        //songs_aux = emptyArray()
        songs = emptyList()
    }

    fun getSongs() : List<Song>{
        return songs
    }
    fun updateSongs(id_songs: List<Long>){

        //songs = songs.toMutableList().add(modelDAO.getSongById(1l))

        val songs_aux = ArrayList<Song>()

        for (item in id_songs) {
            //println(item)
            //println(modelDAO.getSongById(item).name)
            //song = modelDAO.getSongById(1L)
            song = modelDAO.getSongById(item)
            songs_aux.add(song)
            println(songs_aux)
        }

        //songs_aux.toMutableList().add(modelDAO.getSongById(1L))



        songs = songs_aux.toList()
        //songs = modelDAO.getAllSongs()
    }
/*
    private operator fun <Song> Collection<Song>.plus(element: Song): List<Song> {
        val result = ArrayList<Song>(size + 1)
        result.add(element)
        return result
    }
 */
    fun updateSongById(id_song: Long){
        song = modelDAO.getSongById(1L)
    }

    suspend fun deleteById(id: Long?){
        modelDAO.deleteById(id)
    }
}