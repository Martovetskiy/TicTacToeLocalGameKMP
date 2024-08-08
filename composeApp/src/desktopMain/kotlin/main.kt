import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import localtictactoe.composeapp.generated.resources.Res
import localtictactoe.composeapp.generated.resources.expand
import localtictactoe.composeapp.generated.resources.logo
import localtictactoe.composeapp.generated.resources.shrink
import navigation.RootComponent
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    val state = rememberWindowState(
        position = WindowPosition(Alignment.Center), size = DpSize(1280.dp, 768.dp)
    )
    Window(
        state = state,
        resizable = true,
        icon = painterResource(Res.drawable.logo),
        onCloseRequest = ::exitApplication,
        title = "Крестики-Нолики",
        undecorated = true

    ) {
        App(root)

            AppWindowTitleBar(
                state = state,
                onClose = { exitApplication() }
            )

    }
}

@Composable
fun WindowScope.AppWindowTitleBar(
    state: WindowState,
    onClose: () -> Unit,
    onMinimize: () -> Unit = { state.isMinimized = state.isMinimized.not() },
    onMaximize: () -> Unit = {
        state.placement = if (state.placement == WindowPlacement.Maximized)
            WindowPlacement.Floating else WindowPlacement.Maximized
    },
) {
    Box(Modifier.fillMaxWidth()) {
        WindowDraggableArea (modifier = Modifier
            .fillMaxWidth(0.85f)
            .align(Alignment.TopEnd)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(onClick = onMinimize) { }
                val isFloating = state.placement == WindowPlacement.Floating
                IconButton(onClick = onMaximize) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(
                        if (isFloating) Res.drawable.expand else Res.drawable.shrink
                    ), contentDescription = null, tint = readSettings().theme.negativeText)
                }
                IconButton(onClick = onClose) {
                    Icon(Icons.Filled.Clear, contentDescription = null, tint = readSettings().theme.negativeText)
                }
            }
        }
    }
}