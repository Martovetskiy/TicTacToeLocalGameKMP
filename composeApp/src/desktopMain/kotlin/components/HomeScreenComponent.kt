package components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import changeNickname
import com.arkivanov.decompose.ComponentContext
import readSettings

class HomeScreenComponent (
    componentContext: ComponentContext,
    private val onGoHost: () -> Unit,
    private val onGoClient: () -> Unit
) : ComponentContext by componentContext {

    private val _nickname: MutableState<String> = mutableStateOf("Player")

    val nickname = _nickname

    init {
        _nickname.value = readSettings().nickname
    }

    fun saveNick() {
        changeNickname(_nickname.value)
    }

    fun goToHost(){
        onGoHost()
    }

    fun goToClient(){
        onGoClient()
    }
}