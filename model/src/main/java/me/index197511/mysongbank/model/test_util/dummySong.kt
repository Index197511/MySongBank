package me.index197511.mysongbank.model.test_util

import me.index197511.mysongbank.model.Song

fun dummySong(
    id: Int = 100,
    name: String = "",
    singer: String = "",
    key: Int = 0,
    memo: String = ""
): Song = Song(id, name, singer, key, memo)