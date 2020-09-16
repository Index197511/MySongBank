package me.index197511.mysongbank.data.source.local.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.index197511.mysongbank.data.source.local.db.entity.SongEntity

@Dao
interface MySongDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Update
    suspend fun update(song: SongEntity)

    @Query("SELECT * FROM songs ORDER BY id DESC")
    fun getAllSong(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE name LIKE '%' || :query || '%'")
    fun getSongsWithQuery(query: String): Flow<List<SongEntity>>
}