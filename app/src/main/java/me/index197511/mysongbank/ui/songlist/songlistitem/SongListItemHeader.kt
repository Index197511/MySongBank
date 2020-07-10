package me.index197511.mysongbank.ui.songlist.songlistitem

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListItemHeaderBinding
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.ui.songlist.OnClickHandler
import org.koin.core.KoinComponent


class SongListItemHeader(
    private val listener: OnClickHandler,
    private val song: Song
) :
    BindableItem<SongListItemHeaderBinding>(), KoinComponent {

    override fun getLayout(): Int =
        R.layout.song_list_item_header

    override fun bind(viewBinding: SongListItemHeaderBinding, position: Int) {
        viewBinding.songTitle.text = song.name
        viewBinding.singer.text = song.singer

        viewBinding.songListItemRoot.setOnClickListener {
            listener.onRootClick()
        }
        viewBinding.buttonEdit.setOnClickListener {
            listener.onEditClick()
        }

        viewBinding.songListItemRoot.setOnLongClickListener {
            listener.onItemLongClick()
            true
        }

    }

    override fun initializeViewBinding(view: View): SongListItemHeaderBinding =
        SongListItemHeaderBinding.bind(view)

}