import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import localtictactoe.composeapp.generated.resources.Res
import localtictactoe.composeapp.generated.resources.logo
import navigation.RootComponent
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    Window(
        //state = WindowState(width = 1400.dp, height = 800.dp),
        icon = painterResource(Res.drawable.logo),
        onCloseRequest = ::exitApplication,
        title = "Крестики-Нолики",

    ) {
        App(root)
    }
}