package me.index197511.mysongbank.feature.songlist.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.feature.songlist.R
import me.index197511.mysongbank.feature.songlist.databinding.SongListItemHeaderBinding
import me.index197511.mysongbank.model.Song


class SongListItemHeader(
    private val listener: me.index197511.mysongbank.feature.songlist.OnClickHandler,
    private val song: Song
) :
    BindableItem<SongListItemHeaderBinding>() {

    override fun getLayout(): Int =
        R.layout.song_list_item_header

    override fun bind(viewBinding: SongListItemHeaderBinding, position: Int) {
        viewBinding.apply {
            songTitle.text = song.name
            singer.text = song.singer
        }

        viewBinding.apply {
            songListItemRoot.setOnClickListener {
                listener.onRootClick()
            }

            songListItemRoot.setOnLongClickListener {
                listener.onItemLongClick()
                true
            }

            buttonEdit.setOnClickListener {
                listener.onEditClick()
            }
        }
    }

    override fun initializeViewBinding(view: View): SongListItemHeaderBinding =
        SongListItemHeaderBinding.bind(view)

}