package me.index197511.mysongbank.ui.songlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.insert_new_song_dialog.*
import kotlinx.android.synthetic.main.song_list_item_body.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import me.index197511.mysongbank.R
import me.index197511.mysongbank.data.source.local.datastore.SortOption
import me.index197511.mysongbank.databinding.SongListFragmentBinding
import me.index197511.mysongbank.ui.songlist.songlistitem.SongListItemHeader

interface OnClickHandler {
    fun onRootClick()
    fun onEditClick()
    fun onItemLongClick()
}

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SongListFragment : Fragment() {

    private val viewModel by viewModels<SongListViewModel>()
    private lateinit var binding: SongListFragmentBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var sortInitialOption: SortOption

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewSongList.adapter = adapter

        binding.buttonAddNewSong.setOnClickListener {
            showInsertNewSongDialog()
        }

        viewModel.sortedSongs.observe(viewLifecycleOwner, Observer {
            it?.let { updateSongList(it) }
        })

        viewModel.sortOrder.observe(viewLifecycleOwner, Observer {
            sortInitialOption = it
        })

        setUpSearchView()
        viewModel.filterWithQuery("")
    }

    private fun setUpSearchView() {
        binding.floatingSearchView.setOnQueryChangeListener { _, newQuery ->
            viewModel.filterWithQuery(newQuery)
        }
        binding.floatingSearchView.setOnMenuItemClickListener { item: MenuItem? ->
            showSortSettingDialog()
        }
    }

    private fun showSortSettingDialog() {
        context?.let { context ->
            MaterialDialog(context).show {
                listItemsSingleChoice(
                    initialSelection = SortOption.values()
                        .indexOf(sortInitialOption),
                    items = SortOption.values()
                        .map { it.toString() }) { _, _, text ->
                    viewModel.switchSortOption(
                        SortOption.valueOf(
                            text.toString()
                        )
                    )
                }
                positiveButton(R.string.text_apply)
                negativeButton(R.string.text_cancel)
            }
        }
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
                        me.index197511.mysongbank.model.Song(
                            id = 0,
                            name = name,
                            singer = singer,
                            key = key,
                            memo = memo
                        )
                    viewModel.insertSong(newSong)
                }
                negativeButton(R.string.text_cancel)
            }
        }
    }


    private fun updateSongList(songList: List<me.index197511.mysongbank.model.Song>) {
        adapter.update(mutableListOf<Group>().apply {
            songList.forEach { song ->
                val handler = object : OnClickHandler {
                    override fun onRootClick() {
                        context?.let {
                            MaterialDialog(it, BottomSheet()).show {
                                title(R.string.text_dialog_title)
                                customView(R.layout.song_list_item_body)
                                this.text_view_name.text = song.name
                                this.text_view_singer.text = song.singer
                                this.text_view_key.text =
                                    if (song.key <= 0) "${song.key}" else "+${song.key}"
                                this.text_view_memo.text = song.memo
                                negativeButton(R.string.text_close)
                            }
                        }
                    }

                    override fun onEditClick() {
                        val action =
                            SongListFragmentDirections.actionSongListFragmentToEditSongFragment(song)
                        findNavController().navigate(action)
                    }

                    override fun onItemLongClick() {
                        context?.let {
                            MaterialDialog(it).show {
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
                }
                add(SongListItemHeader(handler, song))
            }
        })
    }

}