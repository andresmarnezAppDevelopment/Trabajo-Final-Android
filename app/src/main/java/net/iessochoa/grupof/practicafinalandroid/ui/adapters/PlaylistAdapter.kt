package net.iessochoa.grupof.practicafinalandroid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.databinding.PlaylistItemBinding
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    var playlists: List<Playlist>? = null
    var onListaClickListener: OnPlaylistClickListener? = null

    interface OnPlaylistClickListener {
        fun onListaClick(lista: Playlist?)
        fun onListaBorrarClick(lista: Playlist?)
    }

    inner class PlaylistViewHolder(val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivDelete.setOnClickListener {
                val playlist = playlists?.get(this.adapterPosition)
                onListaClickListener?.onListaBorrarClick(playlist)
            }

            binding.root.setOnClickListener {
                val playlist = playlists?.get(this.adapterPosition)
                onListaClickListener?.onListaClick(playlist)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = PlaylistItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(playlistViewHolder: PlaylistViewHolder, pos: Int) {

        with(playlistViewHolder) {
            with(playlists!!.get(pos)) {

                binding.tvTitle.text = name
            }
        }
    }

    override fun getItemCount(): Int = playlists?.size ?: 0

    fun setLista(lista: List<Playlist>) {
        playlists = lista
        notifyDataSetChanged()
    }

    class PlaylistsComparator : DiffUtil.ItemCallback<Playlist>() {
        override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem.name == newItem.name
        }
    }
}