package me.index197511.mysongbank.db.dao

import androidx.room.*
import me.index197511.mysongbank.db.entity.SongEntity

@Dao
interface MySongDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Query("SELECT * FROM songs ORDER BY id DESC")
    suspend fun getAllSong(): List<SongEntity>
}