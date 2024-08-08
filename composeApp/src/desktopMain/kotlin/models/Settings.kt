package models

import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ui.GreenTheme
import ui.Theme
import java.lang.reflect.Type


class SettingsSerializer : JsonSerializer<Settings?> {
    override fun serialize(src: Settings?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return null
    }
}

data class Settings (
    val theme: Theme
)
{
    constructor() : this( theme = GreenTheme)
}