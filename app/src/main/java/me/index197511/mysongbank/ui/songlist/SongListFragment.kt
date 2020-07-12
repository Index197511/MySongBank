package me.index197511.mysongbank.ui.songlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.insert_new_song_dialog.*
import kotlinx.android.synthetic.main.song_list_item_body.*
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListFragmentBinding
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.ui.songlist.songlistitem.SongListItemHeader
import org.koin.android.viewmodel.ext.android.viewModel

interface OnClickHandler {
    fun onRootClick()
    fun onEditClick()
    fun onItemLongClick()
}

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
                positiveButton(R.string.text_add) {
                    val name = it.edit_text_name.text.toString()
                    val singer = it.edit_text_singer.text.toString()
                    val key = it.edit_text_key.text.toString().toIntOrNull() ?: 0
                    val memo = it.edit_text_memo.text.toString()
                    val newSong =
                        Song(id = 0, name = name, singer = singer, key = key, memo = memo)
                    Log.i("DebugPrint", "$newSong")
                    viewModel.insertSong(newSong)
                }
                negativeButton(R.string.text_cancel)
            }
        }
    }


    private fun updateSongList(songList: List<Song>) {
        adapter.update(mutableListOf<Group>().apply {
            songList.forEach { song ->

                val handler = object : OnClickHandler {
                    override fun onRootClick() {
                        MaterialDialog(context!!, BottomSheet()).show {
                            title(R.string.text_dialog_title)
                            customView(R.layout.song_list_item_body)
                            this.text_view_name.text = song.name
                            this.text_view_singer.text = song.singer
                            this.text_view_key.text =
                                if (song.key <= 0) song.key.toString() else "+${song.key}"
                            this.text_view_memo.text = song.memo
                            negativeButton(R.string.text_close)
                        }
                    }

                    override fun onEditClick() {
                        val action =
                            SongListFragmentDirections.actionSongListFragmentToEditSongFragment(song)
                        findNavController().navigate(action)
                    }

                    override fun onItemLongClick() {
                        MaterialDialog(context!!).show {
                            title(R.string.text_delete_dialog_message)
                            customView(R.layout.delete_song_dialog)
                            this.text_view_name.text = song.name
                            this.text_view_singer.text = song.singer

                            positiveButton(R.string.text_delete) {
                                viewModel.removeSong(song)
                            }
                            negativeButton(R.string.text_cancel)
                        }
                    }
                }

                add(SongListItemHeader(handler, song))
            }
        })
    }

}