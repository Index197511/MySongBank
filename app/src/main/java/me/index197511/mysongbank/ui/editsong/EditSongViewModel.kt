package me.index197511.mysongbank.ui.editsong

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.index197511.mysongbank.data.repository.SongRepository
import me.index197511.mysongbank.model.Song

class EditSongViewModel @ViewModelInject constructor(
    private val repository: SongRepository
) : ViewModel() {
    fun updateSong(song: Song) {
        viewModelScope.launch {
            repository.update(song)
        }
    }
}