package me.index197511.mysongbank.ui.songlist.songlistitem

import android.view.View
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListItemHeaderBinding
import me.index197511.mysongbank.model.Song

class SongListItemHeader(private val song: Song) : BindableItem<SongListItemHeaderBinding>(),
    ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun getLayout(): Int =
        R.layout.song_list_item_header

    override fun bind(viewBinding: SongListItemHeaderBinding, position: Int) {
        viewBinding.songTitle.text = song.name
        viewBinding.singer.text = song.singer

        viewBinding.songListItemRoot.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun initializeViewBinding(view: View): SongListItemHeaderBinding =
        SongListItemHeaderBinding.bind(view)

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}