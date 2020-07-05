package me.index197511.mysongbank.ui.songlist.songlistitem

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListItemBinding
import me.index197511.mysongbank.model.Song

class SongListItem(private val song: Song) : BindableItem<SongListItemBinding>() {
    override fun getLayout(): Int =
        R.layout.song_list_item


    override fun bind(viewBinding: SongListItemBinding, position: Int) {
        viewBinding.songTitle.text = song.name
        viewBinding.singer.text = song.singer
    }

    override fun initializeViewBinding(view: View): SongListItemBinding =
        SongListItemBinding.bind(view)

}