package me.index197511.mysongbank.ui.editsong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import me.index197511.mysongbank.R
import me.index197511.mysongbank.databinding.EditSongFragmentBinding
import me.index197511.mysongbank.model.Song
import org.koin.android.viewmodel.ext.android.viewModel

class EditSongFragment : Fragment() {

    private val args: EditSongFragmentArgs by navArgs()
    private val viewModel by viewModel<EditSongViewModel>()
    private lateinit var binding: EditSongFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditSongFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        binding.editTextName.setText(args.song.name)
        binding.editTextSinger.setText(args.song.singer)
        binding.editTextKey.setText(args.song.key.toString())
        binding.editTextMemo.setText(args.song.memo)

        binding.buttonSave.setOnClickListener {
            val id = args.song.id
            val name = binding.editTextName.text.toString()
            val singer = binding.editTextSinger.text.toString()
            val key = binding.editTextKey.text.toString().toIntOrNull() ?: 0
            val memo = binding.editTextMemo.text.toString()
            val updatedSong =
                Song(id = id, name = name, singer = singer, key = key, memo = memo)
            viewModel.updateSong(updatedSong)
            findNavController().navigate(R.id.action_editSongFragment_to_songListFragment)
        }
        super.onActivityCreated(savedInstanceState)
    }

}