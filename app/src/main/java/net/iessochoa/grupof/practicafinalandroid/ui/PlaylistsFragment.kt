package net.iessochoa.grupof.practicafinalandroid.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentPlaylistsBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.PlaylistViewModel
import net.iessochoa.grupof.practicafinalandroid.ui.adapters.PlaylistAdapter

class PlaylistsFragment : Fragment() {

    private val viewModel: PlaylistViewModel by activityViewModels()
    private var _binding: FragmentPlaylistsBinding? = null
    lateinit var playlistAdapter: PlaylistAdapter


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniciaRecyclerView()
        inicializaCRUD()

        runBlocking{
            playlistAdapter.setLista(viewModel.getAllPlaylists())
        }
    }

    private fun iniciaRecyclerView() {
        playlistAdapter = PlaylistAdapter()

        with(binding.playlistRecycler) {
            layoutManager = LinearLayoutManager(activity)
            adapter = playlistAdapter
        }
    }

    private fun inicializaCRUD(){
        //Creación de una nueva playlist
        binding.fbButton.setOnClickListener{
            //Todo: Cosas para crear la nueva Playlist
        }

        playlistAdapter.onListaClickListener = object : PlaylistAdapter.OnPlaylistClickListener{
            override fun onListaClick(lista: Playlist?) {
                val accion = PlaylistsFragmentDirections.actionPlaylistFragmentToPlaylistDetailsFragment(lista)
                findNavController().navigate(accion)
            }

            override fun onListaBorrarClick(lista: Playlist?) {
                runBlocking {
                    if (lista != null) {
                        viewModel.deleteById(lista.id)
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}