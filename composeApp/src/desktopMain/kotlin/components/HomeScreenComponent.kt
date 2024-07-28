package components

import com.arkivanov.decompose.ComponentContext

class HomeScreenComponent (
    componentContext: ComponentContext,
    private val onGoHost: () -> Unit,
    private val onGoClient: () -> Unit
) : ComponentContext by componentContext {

    fun goToHost(){
        onGoHost()
    }

    fun goToClient(){
        onGoClient()
    }
}