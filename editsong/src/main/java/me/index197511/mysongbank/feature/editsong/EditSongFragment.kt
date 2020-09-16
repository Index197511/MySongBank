package me.index197511.mysongbank.feature.editsong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.index197511.mysongbank.feature.editsong.databinding.EditSongFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class EditSongFragment : Fragment() {
    private val args: EditSongFragmentArgs by navArgs()
    private val viewModel by viewModels<EditSongViewModel>()
    @Inject
    lateinit var router: EditSongRouter
    private lateinit var binding: EditSongFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditSongFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        binding.apply {
            editTextName.setText(args.song.name)
            editTextSinger.setText(args.song.singer)
            editTextKey.setText(args.song.key.toString())
            editTextMemo.setText(args.song.memo)
        }

        binding.apply {
            buttonSave.setOnClickListener {
                val id = args.song.id
                val name = editTextName.text.toString()
                val singer = editTextSinger.text.toString()
                val key = editTextKey.text.toString().toIntOrNull() ?: 0
                val memo = editTextMemo.text.toString()
                val updatedSong =
                    me.index197511.mysongbank.model.Song(
                        id = id,
                        name = name,
                        singer = singer,
                        key = key,
                        memo = memo
                    )
                viewModel.updateSong(updatedSong)
            }
            router.navToSongListFragment()
        }
        super.onActivityCreated(savedInstanceState)
    }

}