package me.index197511.mysongbank.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.index197511.mysongbank.model.Song

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "singer")
    val singer: String,

    @ColumnInfo(name = "key")
    val key: Int,

    @ColumnInfo(name = "memo")
    val memo: String
) {
    fun toModel(): Song =
        Song(id, name, singer, key, memo)
}

fun Song.toEntity(): SongEntity =
    SongEntity(id, name, singer, key, memo)
