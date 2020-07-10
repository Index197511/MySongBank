package me.index197511.mysongbank.ui.editsong

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.index197511.mysongbank.data.SongRepository
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent
import org.koin.core.inject

class EditSongViewModel : ViewModel(), KoinComponent {
    private val repository by inject<SongRepository>()

    fun updateSong(song: Song) {
        viewModelScope.launch {
            repository.update(song)
        }
    }
}