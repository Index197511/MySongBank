package me.index197511.mysongbank.ui.songlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import me.index197511.mysongbank.databinding.SongListFragmentBinding
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.ui.songlist.songlistitem.SongListItem
import org.koin.android.viewmodel.ext.android.viewModel

class SongListFragment : Fragment() {

    private val viewModel by viewModel<SongListViewModel>()
    private lateinit var binding: SongListFragmentBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerViewSongList.adapter = adapter

        binding.buttonAddNewSong.setOnClickListener {
            viewModel.insertSong()
        }

        viewModel.songs.observe(viewLifecycleOwner, Observer {
            it?.let { updateSongList(it) }
        })
    }

    private fun updateSongList(songList: List<Song>) {
        adapter.update(mutableListOf<Group>().apply {
            songList.forEach { song ->
                add(SongListItem(song))
            }
        })
    }

}