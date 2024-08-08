import com.google.gson.Gson
import com.google.gson.GsonBuilder
import models.Settings
import models.SettingsSerializer
import ui.GreenTheme
import ui.PurpleTheme
import ui.Theme
import ui.ThemeSerializer
import java.io.File
import java.io.FileReader

fun checkIPAddress(input: String): Boolean {
    val regex = Regex("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
    return regex.matches(input)
}

fun readSettings() : Settings
{
    val file = File("settings.json")

    var settings: Settings

    val gson = GsonBuilder()
        .registerTypeAdapter(Theme::class.java, ThemeSerializer())
        .registerTypeAdapter(Settings::class.java, SettingsSerializer())
        .create()

    if (file.exists()) {
        FileReader(file).use {
            settings =  gson.fromJson(it, Settings::class.java)
        }
    }
    else
    {
        file.writeText(
            Gson().toJson(Settings(GreenTheme))
        )
        settings = Settings(GreenTheme)
    }

    return settings
}

fun changeTheme(currentTheme: Theme) : Settings {
    val listThemes: List<Theme> = listOf(GreenTheme, PurpleTheme)
    val indexNextTheme = if (listThemes.indexOf(currentTheme) == listThemes.size-1) 0 else listThemes.indexOf(currentTheme) + 1

    val settings = Settings(theme = listThemes[indexNextTheme])
    val gson = Gson()

    File("settings.json").writeText(
        gson.toJson(
            settings
        )
    )

    return settings
}
