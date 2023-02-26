package net.iessochoa.grupof.practicafinalandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.runBlocking
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentPlaylistDetailsBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
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
            songAdapter.setLista(viewModel.getAllSongs())
        }
    }

    private fun cargaInfoPlaylist(playlist: Playlist) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "${playlist.name}"
        binding.tvPlaylistTitleName.text = playlist.name
        //binding.tvExample.text = playlist.id_songs.toString()
    }

    private fun iniciaRecyclerView() {
        songAdapter = SongAdapter()

        with(binding.songsRecycler) {
            layoutManager = LinearLayoutManager(activity)
            adapter = songAdapter
        }
    }
}