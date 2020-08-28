package me.index197511.mysongbank.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.index197511.mysongbank.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.db.entity.toEntity
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent
import org.koin.core.inject

interface SongRepositoryInterface {
    suspend fun add(song: Song)
    suspend fun remove(song: Song)
    fun loadAll(): Flow<List<Song>>
    suspend fun update(song: Song)
}

class SongRepository : SongRepositoryInterface, KoinComponent {
    private val songDao by inject<MySongDatabaseDao>()

    override suspend fun add(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.insertSong(song.toEntity())
        }
    }

    override suspend fun remove(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.deleteSong(song.toEntity())
        }
    }

    override fun loadAll(): Flow<List<Song>> {
        return songDao.getAllSong()
            .map { songs -> songs.map { songEntity -> songEntity.toModel() } }
    }

    override suspend fun update(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.update(song.toEntity())
        }
    }
}