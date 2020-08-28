package me.index197511.mysongbank.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.index197511.mysongbank.db.entity.SongEntity

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
}