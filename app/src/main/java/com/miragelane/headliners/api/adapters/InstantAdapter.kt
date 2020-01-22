package com.miragelane.headliners.api.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.Instant

/**
 * Converts between org.threeten.bp.Instant and json objects.
 */
class InstantAdapter: TypeAdapter<Instant>() {

    override fun write(out: JsonWriter?, value: Instant?) {
        out?.also { jsonWriter ->
            value?.also { instant ->
                jsonWriter.value(instant.toEpochMilli())
            }
        }
    }

    override fun read(jsonReader: JsonReader?): Instant? {
        return try {
            val long = jsonReader?.nextLong() ?: 0L
            Instant.ofEpochMilli(long)
        }
        catch (e: IllegalStateException) {
            jsonReader?.nextNull()
            null
        }
    }
}