package com.miragelane.headliners.api.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter

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
        return jsonReader?.nextString()?.let {
            try {
                Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(it))
            }
            catch (e: IllegalStateException) {
                null
            }
        }
    }
}