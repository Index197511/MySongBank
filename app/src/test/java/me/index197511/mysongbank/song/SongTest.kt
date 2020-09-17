package me.index197511.mysongbank.song

import com.google.common.truth.Truth.assertThat
import me.index197511.mysongbank.data.source.local.db.entity.SongEntity
import me.index197511.mysongbank.data.source.local.db.entity.toEntity
import me.index197511.mysongbank.model.Song
import org.junit.Test

class SongTest {
    @Test
    fun songToEntity() {
        val songModel = Song(
            id = 0,
            name = "Name",
            singer = "Singer",
            key = 10,
            memo = "Memo"
        )
        val songEntity = songModel.toEntity()
        assertThat(songEntity).isInstanceOf(SongEntity::class.java)
        assertThat(songEntity).isEqualTo(
            SongEntity(
                id = 0,
                name = "Name",
                singer = "Singer",
                key = 10,
                memo = "Memo"
            )
        )
    }

    @Test
    fun songToModel() {
        val songEntity =
            SongEntity(
                id = 0,
                name = "Name",
                singer = "Singer",
                key = 10,
                memo = "Memo"
            )
        val songModel = songEntity.toModel()
        assertThat(songModel).isInstanceOf(Song::class.java)
        assertThat(songModel).isEqualTo(
            Song(
                id = 0,
                name = "Name",
                singer = "Singer",
                key = 10,
                memo = "Memo"
            )
        )
    }
}