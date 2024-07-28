package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import components.ClientScreenComponent
import components.HomeScreenComponent
import components.HostScreenComponent
import components.InputAddressScreenComponent
import kotlinx.serialization.Serializable

class RootComponent (
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.HomeScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ) : Child{
        return when(config){
            Configuration.HomeScreen -> Child.HomeScreen(
                HomeScreenComponent(
                    componentContext = context,
                    onGoHost = {
                        navigation.pushNew(Configuration.HostScreen)
                    },
                    onGoClient = {
                        navigation.pushNew(Configuration.InputAddressScreen)
                    }
                )
            )
            Configuration.HostScreen -> Child.HostScreen(
                HostScreenComponent(
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )

            Configuration.InputAddressScreen -> Child.InputAddressScreen(
                InputAddressScreenComponent(
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    },
                    onGoClient = {address, port ->
                        navigation.pushNew(Configuration.ClientScreen(address, port))
                    }
                )
            )

            is Configuration.ClientScreen -> Child.ClientScreen(
                ClientScreenComponent(
                    componentContext = context,
                    address = config.address,
                    port = config.port,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )



        }
    }

    sealed class Child {
        data class HomeScreen(val component: HomeScreenComponent) : Child()
        data class HostScreen(val component: HostScreenComponent) : Child()
        data class InputAddressScreen(val componentContext: InputAddressScreenComponent) : Child()
        data class ClientScreen(val componentContext: ClientScreenComponent) : Child()
    }



    @Serializable
    sealed class Configuration {
        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data object HostScreen : Configuration()

        @Serializable
        data object InputAddressScreen : Configuration()

        @Serializable
        data class ClientScreen(val address: String, val port: Int) : Configuration()
    }
}