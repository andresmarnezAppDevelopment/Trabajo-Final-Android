package net.iessochoa.grupof.practicafinalandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentPlaylistDetailsBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class PlaylistDetailsFragment : Fragment() {
    private var _binding: FragmentPlaylistDetailsBinding? = null
    private val binding get() = _binding!!
    val argumentos: PlaylistDetailsFragmentArgs by navArgs()

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
    }

    private fun cargaInfoPlaylist(playlist: Playlist) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "${playlist.name}"
        binding.tvPlaylistTitleName.text = playlist.name
        //binding.tvExample.text = playlist.id_songs.toString()
    }
}