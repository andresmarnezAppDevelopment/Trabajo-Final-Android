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
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentPlaylistsBinding
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

        /*
        viewModel.listasLiveData.observe(viewLifecycleOwner) { lista ->
            //actualizaLista(lista)
            listasAdapter.setLista(lista)
        }
         */
    }

    private fun iniciaRecyclerView() {
        //creamos el adaptador
        playlistAdapter = PlaylistAdapter()

        with(binding.playlistRecycler) {
            layoutManager = LinearLayoutManager(activity)
            adapter = playlistAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun actualizaLista(lista: List<Song>?) {
        var listaString = ""
        lista?.forEach() {
            listaString =
                "$listaString ${it.id}-${it.name}-${it.album}-${it.artist}-${it.year}\n"
        }
    }
}