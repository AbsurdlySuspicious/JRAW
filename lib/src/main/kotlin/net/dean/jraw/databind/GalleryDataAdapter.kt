package net.dean.jraw.databind

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonReader.Token
import com.squareup.moshi.JsonWriter
import net.dean.jraw.models.GalleryData

class GalleryDataAdapter: JsonAdapter<GalleryData>() {
    override fun fromJson(r: JsonReader): GalleryData? {
        if (r.peek() != Token.BEGIN_OBJECT)
            return null
        r.beginObject()

        while (r.peek() != Token.END_OBJECT) {
            if (r.peek() == Token.NAME && r.nextName() == "items") break
            else r.skipValue()
        }

        if (r.peek() != Token.BEGIN_ARRAY)
            return null
        r.beginArray()

        val items = arrayListOf<String>()

        while (r.peek() != Token.END_ARRAY) {
            if (r.peek() != Token.BEGIN_OBJECT) {
                r.skipValue()
                continue
            }
            r.beginObject()

            while (r.peek() != Token.END_OBJECT) {
                if (r.peek() == Token.NAME && r.nextName() == "media_id") items.add(r.nextString())
                else r.skipValue()
            }
            r.endObject()
        }
        r.endArray()

        while (r.peek() != Token.END_OBJECT)
            r.skipValue()
        r.endObject()

        return GalleryData(items)
    }

    override fun toJson(w: JsonWriter, d: GalleryData?) {
        w.beginObject()
        w.name("items")
        w.beginArray()

        d?.mediaIds?.forEach {
            w.beginObject()
            w.name("media_id")
            w.value(it)
            w.endObject()
        }

        w.endArray()
        w.endObject()
    }
}
