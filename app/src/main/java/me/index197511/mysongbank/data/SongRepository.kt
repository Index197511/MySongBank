package me.index197511.mysongbank.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.index197511.mysongbank.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.db.entity.toEntity
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent
import org.koin.core.inject

interface SongRepositoryInterface {
    suspend fun add(song: Song)
    suspend fun remove(song: Song)
    suspend fun loadAll(): List<Song>
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

    override suspend fun loadAll(): List<Song> {
        return withContext(Dispatchers.IO) {
            songDao.getAllSong().map { e -> e.toModel() }
        }
    }

    override suspend fun update(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.update(song.toEntity())
        }
    }
}