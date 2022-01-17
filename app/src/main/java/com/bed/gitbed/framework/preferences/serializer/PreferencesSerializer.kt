package com.bed.gitbed.framework.preferences.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.bed.gitbed.DataPreferences
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object PreferencesSerializer : Serializer<DataPreferences> {
    override val defaultValue: DataPreferences = DataPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DataPreferences {
        try {
            return DataPreferences.parseFrom(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: DataPreferences, output: OutputStream) = t.writeTo(output)

}