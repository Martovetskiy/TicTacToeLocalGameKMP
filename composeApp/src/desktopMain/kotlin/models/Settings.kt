package models

import ui.GreenTheme
import ui.Theme

data class Settings (
    val theme: Theme
)
{
    constructor() : this( theme = GreenTheme)
}