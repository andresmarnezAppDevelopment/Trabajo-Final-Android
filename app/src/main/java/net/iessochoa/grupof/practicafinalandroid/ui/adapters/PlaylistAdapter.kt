package net.iessochoa.grupof.practicafinalandroid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.model.Playlist

class PlaylistAdapter : ListAdapter<Playlist, PlaylistAdapter.PlaylistHolder>(PlaylistsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        return PlaylistHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)

    }

    class PlaylistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playlistItemView: TextView = itemView.findViewById(R.id.tvTitle)

        fun bind(text: String?) {
            playlistItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): PlaylistHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.playlist_item, parent, false)
                return PlaylistHolder(view)
            }
        }
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