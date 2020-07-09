package me.index197511.mysongbank.ui.songlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.insert_new_song_dialog.*
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListFragmentBinding
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.ui.songlist.songlistitem.SongListItemHeader
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
            showInsertNewSongDialog()
        }

        viewModel.songs.observe(viewLifecycleOwner, Observer {
            it?.let { updateSongList(it) }
        })
    }

    private fun showInsertNewSongDialog() {
        context?.let { context ->
            MaterialDialog(context, BottomSheet()).show {
                title(R.string.text_dialog_title)
                customView(R.layout.insert_new_song_dialog)
                positiveButton {
                    val name = it.edit_text_name.text.toString()
                    val singer = it.edit_text_singer.text.toString()
                    val key = it.edit_text_key.text.toString().toIntOrNull() ?: 0
                    val memo = it.edit_text_memo.text.toString()
                    val newSong =
                        Song(id = 0, name = name, singer = singer, key = key, memo = memo)
                    Log.i("DebugPrint", "$newSong")
                    viewModel.insertSong(newSong)
                }
                negativeButton()
            }
        }
    }


    private fun updateSongList(songList: List<Song>) {
        adapter.update(mutableListOf<Group>().apply {
            songList.forEach { song ->
                add(SongListItemHeader(context!!, song))
            }
        })
    }

}