package net.iessochoa.grupof.practicafinalandroid.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.runBlocking
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentPlaylistDetailsBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.PlaylistViewModel
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.SongViewModel
import net.iessochoa.grupof.practicafinalandroid.ui.adapters.PlaylistAdapter
import net.iessochoa.grupof.practicafinalandroid.ui.adapters.SongAdapter

class PlaylistDetailsFragment : Fragment() {

    private val viewModel: SongViewModel by activityViewModels()
    private var _binding: FragmentPlaylistDetailsBinding? = null
    private val binding get() = _binding!!
    val argumentos: PlaylistDetailsFragmentArgs by navArgs()
    lateinit var songAdapter: SongAdapter
    lateinit var id_songs: List<Long>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargaInfoPlaylist(argumentos.playlist!!)
        iniciaRecyclerView()
        runBlocking{
            songAdapter.setLista(viewModel.getAllSongs(id_songs))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargaInfoPlaylist(playlist: Playlist) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "${playlist.name}"
        binding.tvPlaylistTitleName.text = playlist.name
        id_songs= playlist.id_songs
    }

    private fun iniciaRecyclerView() {
        songAdapter = SongAdapter()

        with(binding.songsRecycler) {
            layoutManager = LinearLayoutManager(activity)
            adapter = songAdapter
        }
    }

    private fun inicializaCRUD(){
        //Creación de una nueva playlist
        /*binding.fbButton.setOnClickListener{
            //Todo: Cosas para crear la nueva Playlist
        }

         */

        songAdapter.onListaClickListener = object : SongAdapter.onSongClickListener{
            /*override fun onListaClick(lista: Song?) {
                val accion = PlaylistsFragmentDirections.actionPlaylistFragmentToPlaylistDetailsFragment(lista)
                findNavController().navigate(accion)
            }

             */



            override fun onListaBorrarClick(lista: Song?) {
                borrarSong(lista)
            }

        }
    }

    fun borrarSong(lista: Song?){
        if(lista != null) {
            AlertDialog.Builder(activity as Context)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(getString(R.string.songDeletionWarning, lista.name))
                .setPositiveButton(android.R.string.ok){v,_->
                    runBlocking {
                        viewModel.deleteById(lista.id)
                        songAdapter.setLista(viewModel.getAllSongs(id_songs))
                    }
                    v.dismiss()
                }
                //Comportamiento en caso de que se pulse el botón de cancelar, en el que simplemente cerramos el diálogo
                .setNegativeButton(android.R.string.cancel){v,_->v.dismiss()}
                .setCancelable(false)
                .create()
                .show()
        }
    }
}