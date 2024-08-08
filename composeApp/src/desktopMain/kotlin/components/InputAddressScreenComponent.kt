package components

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext

class InputAddressScreenComponent(
    componentContext: ComponentContext,
    text: String,
    private val onGoClient: (String, Int) -> Unit,
    private val onGoBack: () -> Unit
) : ComponentContext by componentContext {

    private val _address: MutableState<String> = mutableStateOf("")
    private val _port: MutableState<String> = mutableStateOf("")

    private val _secretCounter: MutableIntState = mutableIntStateOf(0)

    val address = _address
    val port = _port
    val textButton = text
    val secretCounter = _secretCounter

    fun goClient(){
        onGoClient(_address.value, _port.value.toInt())
    }

    fun goBack(){
        onGoBack()
    }
}