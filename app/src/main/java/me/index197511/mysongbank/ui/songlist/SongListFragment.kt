package me.index197511.mysongbank.ui.songlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import me.index197511.mysongbank.databinding.SongListFragmentBinding

class SongListFragment : Fragment() {

    private lateinit var viewModel: SongListViewModel
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
            Log.i("DebugPrint", "buttonAddNewSong clicked")
        }
    }

}