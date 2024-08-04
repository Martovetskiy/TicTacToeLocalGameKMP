import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.google.gson.Gson
import models.Settings
import ui.GreenTheme
import ui.PurpleTheme
import ui.Theme
import java.io.File

fun checkIPAddress(input: String): Boolean {
    val regex = Regex("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
    return regex.matches(input)
}

fun readSettings() : Settings
{
    val file = File("settings.json")

    val settings = if (file.exists()) {
        val settingsJson = file.readText()
        Gson().fromJson(settingsJson, Settings::class.java)
    } else {
        Settings(theme = GreenTheme)
    }

    if (!file.exists()) {
        val gson = Gson()
        val settingsJson = gson.toJson(settings)
        file.writeText(settingsJson)
    }

    return settings
}


fun changeTheme(currentTheme: Theme) : Settings {
    val listThemes: List<Theme> = listOf(GreenTheme, PurpleTheme)
    val indexNextTheme = if (listThemes.indexOf(currentTheme) == listThemes.size-1) 0 else listThemes.indexOf(currentTheme) + 1

    val settings = Settings(theme = listThemes[indexNextTheme])
    val gson = Gson()
    val settingsJson = gson.toJson(settings)

    val file = File("settings.json")
    file.writeText(settingsJson)

    return settings
}

fun imageFromFile(file: File): ImageBitmap {
    return org.jetbrains.skia.Image.makeFromEncoded(file.readBytes()).toComposeImageBitmap()
}