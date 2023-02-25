@file:Suppress("OverrideDeprecatedMigration")

package net.iessochoa.grupof.practicafinalandroid.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout.Directions
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
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentSongSearchBinding
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.PlaylistViewModel
import net.iessochoa.grupof.practicafinalandroid.ui.adapters.PlaylistAdapter

class PlaylistsFragment : Fragment() {
    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!
    private val playlistViewModel: PlaylistViewModel by activityViewModels()
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inicializarRecyclerView()

        binding.fbButton.setOnClickListener(){
            val accion = PlaylistsFragmentDirections.actionPlaylistFragmentToSongSearchFragment()
            findNavController().navigate(accion)
        }
    }

    private fun inicializarRecyclerView() {
        //Creamos una variable para poder inicializar el adaptador
        playlistAdapter = PlaylistAdapter()

        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(activity)
            adapter = playlistAdapter
        }
    }


}