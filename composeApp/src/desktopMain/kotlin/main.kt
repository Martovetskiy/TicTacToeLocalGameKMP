import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import navigation.RootComponent

fun main() = application {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    Window(
        //state = WindowState(width = 1400.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
        title = "LocalTicTacToe",

    ) {
        App(root)
    }
}