package ui

import androidx.compose.ui.graphics.Color


data class Theme(
    val background: Color,
    val card: Color,
    val text: Color,
    val accent: Color,
    val negativeText: Color = Light,

    val name: String = "Undefined",
    )

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
val LightGreen = Color(0xFF8DE5DB)
//val WhiteGreen = Color(0xFFB2F0E8)

val Morena = Color(0xFF1F7062)


val PurpleTheme = Theme(
    background = DarkPurple,
    card = LightPurple,
    text = SkyColor,
    accent = Carbon,
    name = "Киберпанк"
)

val GreenTheme =  Theme(
    background = DarknessGreen,
    card = LightGreen,
    text = DarkGreen,
    accent = Morena,
    name = "Летняя"
)

