package me.index197511.mysongbank.data.datastore

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import me.index197511.mysongbank.SortPreferences
import java.io.InputStream
import java.io.OutputStream

object SortPreferencesSerializer : Serializer<SortPreferences> {
    override fun readFrom(input: InputStream): SortPreferences {
        try {
            return SortPreferences.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Read failed", e)
        }
    }

    override fun writeTo(t: SortPreferences, output: OutputStream) = t.writeTo(output)
}