package me.index197511.mysongbank.data.test_util

import me.index197511.mysongbank.data.source.local.db.entity.SongEntity

fun dummySongEntity(
    id: Int = 100,
    name: String = "",
    singer: String = "",
    key: Int = 0,
    memo: String = ""
): SongEntity = SongEntity(id, name, singer, key, memo)
