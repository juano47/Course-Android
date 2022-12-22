package com.delaiglesia.newsapp.data.db

import androidx.room.TypeConverter
import com.delaiglesia.newsapp.data.model.Source

//usamos el TypeConverter para convertir el objeto Source en un String y viceversa
//porque no queremos guardar Source en otra tabla, sino que queremos guardar unicamente name
class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}