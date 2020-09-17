package me.index197511.mysongbank.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.index197511.mysongbank.data.source.local.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.data.source.local.db.entity.toEntity
import me.index197511.mysongbank.model.Song
import javax.inject.Inject

interface SongRepositoryInterface {
    suspend fun add(song: Song)
    suspend fun remove(song: Song)
    fun loadAll(): Flow<List<Song>>
    fun loadSongsWithQuery(query: String): Flow<List<Song>>
    suspend fun update(song: Song)
}

class SongRepository @Inject constructor(private val songDao: MySongDatabaseDao) :
    SongRepositoryInterface {

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

    override fun loadSongsWithQuery(query: String): Flow<List<Song>> {
        return songDao.getSongsWithQuery(query)
            .map { songs -> songs.map { songEntity -> songEntity.toModel() } }
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    override suspend fun update(song: me.index197511.mysongbank.model.Song) {
        withContext(Dispatchers.IO) {
            songDao.update(song.toEntity())
        }
    }
}