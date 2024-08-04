package resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Cross: ImageVector
    get() {
        if (_Cross != null) {
            return _Cross!!
        }
        _Cross = ImageVector.Builder(
            name = "Cross",
            defaultWidth = 140.dp,
            defaultHeight = 140.dp,
            viewportWidth = 140f,
            viewportHeight = 140f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(140f, 8.67f)
                curveTo(140f, 6.33f, 139.09f, 4.13f, 137.44f, 2.48f)
                curveTo(134.13f, -0.83f, 128.37f, -0.83f, 125.06f, 2.48f)
                lineTo(70f, 57.58f)
                lineTo(14.94f, 2.48f)
                curveTo(11.63f, -0.83f, 5.87f, -0.83f, 2.56f, 2.48f)
                curveTo(0.91f, 4.13f, 0f, 6.33f, 0f, 8.67f)
                curveTo(0f, 11.01f, 0.91f, 13.21f, 2.56f, 14.86f)
                lineTo(57.63f, 69.96f)
                lineTo(2.56f, 125.06f)
                curveTo(0.91f, 126.71f, 0f, 128.91f, 0f, 131.24f)
                curveTo(0f, 133.58f, 0.91f, 135.78f, 2.56f, 137.43f)
                curveTo(5.87f, 140.74f, 11.63f, 140.74f, 14.94f, 137.43f)
                lineTo(70f, 82.34f)
                lineTo(125.06f, 137.43f)
                curveTo(126.72f, 139.09f, 128.91f, 140f, 131.25f, 140f)
                curveTo(133.59f, 140f, 135.78f, 139.09f, 137.44f, 137.43f)
                curveTo(139.09f, 135.78f, 140f, 133.58f, 140f, 131.24f)
                curveTo(140f, 128.91f, 139.09f, 126.71f, 137.44f, 125.06f)
                lineTo(82.37f, 69.96f)
                lineTo(137.44f, 14.86f)
                curveTo(139.09f, 13.21f, 140f, 11.01f, 140f, 8.67f)
                close()
            }
        }.build()

        return _Cross!!
    }

@Suppress("ObjectPropertyName")
private var _Cross: ImageVector? = null
