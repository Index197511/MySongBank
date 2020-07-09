package me.index197511.mysongbank.ui.songlist.songlistitem

import android.content.Context
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.android.synthetic.main.song_list_item_body.*
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListItemHeaderBinding
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent

class SongListItemHeader(private val context: Context, private val song: Song) :
    BindableItem<SongListItemHeaderBinding>(), KoinComponent {
    private lateinit var expandableGroup: ExpandableGroup

    override fun getLayout(): Int =
        R.layout.song_list_item_header

    override fun bind(viewBinding: SongListItemHeaderBinding, position: Int) {
        viewBinding.songTitle.text = song.name
        viewBinding.singer.text = song.singer

        viewBinding.songListItemRoot.setOnClickListener {
            MaterialDialog(context, BottomSheet()).show {
                title(R.string.app_name)
                customView(R.layout.song_list_item_body)
                this.text_view_name.text = song.name
                this.text_view_singer.text = song.singer
                this.text_view_key.text = song.key.toString()
                this.text_view_memo.text = song.memo
            }
        }
    }

    override fun initializeViewBinding(view: View): SongListItemHeaderBinding =
        SongListItemHeaderBinding.bind(view)

}