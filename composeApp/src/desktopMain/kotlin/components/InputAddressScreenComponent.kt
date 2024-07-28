package components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext

class InputAddressScreenComponent(
    componentContext: ComponentContext,
    private val text: String,
    private val onGoClient: (String, Int) -> Unit,
    private val onGoBack: () -> Unit
) : ComponentContext by componentContext {

    private val _address: MutableState<String> = mutableStateOf("")
    private val _port: MutableState<String> = mutableStateOf("")

    val address = _address
    val port = _port
    val textButton = text

    fun goClient(){
        onGoClient(_address.value, _port.value.toInt())
    }

    fun goBack(){
        onGoBack()
    }
}