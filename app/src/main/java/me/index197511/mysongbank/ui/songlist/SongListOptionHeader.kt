package me.index197511.mysongbank.ui.songlist

import android.view.View
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListOptionHeaderBinding

class SongListOptionHeader : BindableItem<SongListOptionHeaderBinding>(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun getLayout(): Int = R.layout.song_list_option_header
    override fun bind(viewBinding: SongListOptionHeaderBinding, position: Int) {
        viewBinding.songListOptionRoot.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun initializeViewBinding(view: View): SongListOptionHeaderBinding =
        SongListOptionHeaderBinding.bind(view)

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

}