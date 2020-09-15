package me.index197511.mysongbank.ui.songlist.songlistoption

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.SongListOptionBodyBinding

interface OnClickListener {
    fun onClick(key: String)
}

class SongListOptionBody(
    private val optionName: String,
    private val optionItems: List<String>,
    private val onClickListener: OnClickListener
) :
    BindableItem<SongListOptionBodyBinding>() {
    override fun getLayout(): Int = R.layout.song_list_option_body
    override fun bind(viewBinding: SongListOptionBodyBinding, position: Int) {
        viewBinding.optionName.text = optionName
        viewBinding.sortOption.apply {
            setItems(optionItems)
            setOnItemSelectedListener { _, _, _, item ->
                onClickListener.onClick(item.toString())
            }
        }
    }

    override fun initializeViewBinding(view: View): SongListOptionBodyBinding =
        SongListOptionBodyBinding.bind(view)
}