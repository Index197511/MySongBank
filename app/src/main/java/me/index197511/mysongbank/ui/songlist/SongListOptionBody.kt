package me.index197511.mysongbank.ui.songlist

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListOptionBodyBinding

interface onClickListener {
    fun onClick(key: String)
}

class SongListOptionBody(private val onClickListener: onClickListener) :
    BindableItem<SongListOptionBodyBinding>() {
    override fun getLayout(): Int = R.layout.song_list_option_body
    override fun bind(viewBinding: SongListOptionBodyBinding, position: Int) {
        viewBinding.optionName.text = "SORT"
        viewBinding.sortOption.apply {
            setItems("ID", "NAME", "SINGER", "KEY")
            setOnItemSelectedListener { _, _, _, item ->
                onClickListener.onClick(item.toString())
            }
        }
    }

    override fun initializeViewBinding(view: View): SongListOptionBodyBinding =
        SongListOptionBodyBinding.bind(view)
}