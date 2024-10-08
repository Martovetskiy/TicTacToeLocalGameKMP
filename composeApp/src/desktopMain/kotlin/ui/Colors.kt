package ui

import androidx.compose.ui.graphics.Color
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import localtictactoe.composeapp.generated.resources.Res
import localtictactoe.composeapp.generated.resources.greenBack
import localtictactoe.composeapp.generated.resources.purpleBack
import org.jetbrains.compose.resources.DrawableResource
import java.lang.reflect.Type

class ThemeSerializer : JsonSerializer<Theme?> {
    override fun serialize(src: Theme?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return null
    }
}

data class Theme(
    val background: Color,
    val card: Color,
    val text: Color,
    val accent: Color,
    val negativeText: Color = Light,
    val name: String = "Undefined",
    val butColor: Color,

    val imageBack: DrawableResource = Res.drawable.greenBack
    )
{
    constructor() : this(
        background = Color.Black,
        card = Color.Black,
        text = Color.Black,
        accent = Color.Black,
        negativeText = Color.Black,
        name = "Undefined",
        butColor = Color.Black,

        imageBack = Res.drawable.greenBack
    )
}

//val Dark = Color(0xFF262626)
val Light = Color(0xFFFFFFFF)

val DarkPurple = Color(0xFF522B5B)
val SkyColor = Color(0xFF2B124C)

val LightPurple = Color(0xFFFAF4FB)
val Carbon = Color(0xFF251D3E)
//val Purple  = Color(0xFFB379CA)

val DarknessGreen = Color(0xFF117C6F)
val DarkGreen =  Color(0xFF11533E)
//val Green = Color(0xFF289CBE)
//val SoftGreen = Color(0xFF2FC4B2)
val LightGreen = Color(0xFFC0DFD3)
val CarbonGreen = Color(0xFF165024)
//val WhiteGreen = Color(0xFFB2F0E8)

val Morena = Color(0xFF1F7062)


val PurpleTheme = Theme(
    background = DarkPurple,
    card = LightPurple,
    text = SkyColor,
    accent = Carbon,
    name = "Purple",
    butColor = Carbon,

    imageBack = Res.drawable.purpleBack
)

val GreenTheme =  Theme(
    background = DarknessGreen,
    card = LightGreen,
    text = DarkGreen,
    accent = Morena,
    name = "Summer",
    butColor = CarbonGreen,

    imageBack = Res.drawable.greenBack
)

