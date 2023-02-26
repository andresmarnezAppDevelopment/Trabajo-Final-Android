package net.iessochoa.grupof.practicafinalandroid.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.iessochoa.grupof.practicafinalandroid.databinding.PlaylistItemBinding
import net.iessochoa.grupof.practicafinalandroid.databinding.SongsItemBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist
import net.iessochoa.grupof.practicafinalandroid.model.Song

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongViewHolder>()  {
    var songs: List<Song>? = null
    var onListaClickListener: SongAdapter.OnSongClickListener? = null

    inner class SongViewHolder(val binding: SongsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivDelete.setOnClickListener {
                val song = songs?.get(this.adapterPosition)
                onListaClickListener?.onListaBorrarClick(song)
            }

            binding.root.setOnClickListener {
                val song = songs?.get(this.adapterPosition)
                onListaClickListener?.onListaClick(song)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.SongViewHolder {
        val binding = SongsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(songViewHolder: SongAdapter.SongViewHolder, pos: Int) {

        with(songViewHolder) {
            with(songs!!.get(pos)) {

                binding.tvTitle.text = name
                binding.tvArtist.text = artist
                binding.tvYear.text = year
                binding.tvAlbum.text = album
            }
        }
    }

    override fun getItemCount(): Int = songs?.size ?: 0

    fun setLista(lista: List<Song>) {
        songs = lista
        notifyDataSetChanged()
    }

    interface OnSongClickListener {
        fun onListaClick(lista: Song?)
        fun onListaBorrarClick(lista: Song?)
    }



}