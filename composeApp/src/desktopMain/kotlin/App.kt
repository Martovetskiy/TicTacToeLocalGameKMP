
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import navigation.RootComponent
import ui.ClientScreen
import ui.HomeScreen
import ui.HostScreen
import ui.InputAddressScreen

@Composable
@Preview
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        )
        {
            child->
            when(val instance = child.instance){
                is RootComponent.Child.HomeScreen -> HomeScreen(instance.component)
                is RootComponent.Child.HostScreen -> HostScreen(instance.component)
                is RootComponent.Child.InputAddressScreen -> InputAddressScreen(instance.componentContext)
                is RootComponent.Child.ClientScreen -> ClientScreen(instance.componentContext)
            }
        }
    }
}