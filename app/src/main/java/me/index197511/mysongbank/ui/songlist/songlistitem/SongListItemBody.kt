package me.index197511.mysongbank.ui.songlist.songlistitem

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListItemBodyBinding
import me.index197511.mysongbank.model.Song

class SongListItemBody(private val song: Song) : BindableItem<SongListItemBodyBinding>() {
    override fun getLayout(): Int = R.layout.song_list_item_body

    override fun bind(viewBinding: SongListItemBodyBinding, position: Int) {
        viewBinding.songsKey.text = "Key: ${song.key}"
        viewBinding.songsMemo.text = song.memo
    }

    override fun initializeViewBinding(view: View): SongListItemBodyBinding =
        SongListItemBodyBinding.bind(view)
}