@file:Suppress("OverrideDeprecatedMigration")

package net.iessochoa.grupof.practicafinalandroid.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.PlaylistViewModel
import net.iessochoa.grupof.practicafinalandroid.ui.adapters.PlaylistAdapter

class PlaylistsFragment : Fragment() {

    private lateinit var viewModel: PlaylistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PlaylistAdapter()
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        return inflater.inflate(R.layout.fragment_playlists, container, false)
    }


}