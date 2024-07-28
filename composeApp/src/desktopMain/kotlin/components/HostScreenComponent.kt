package components

import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.ComponentContext
import core.GameCore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HostScreenComponent(
    componentContext: ComponentContext,
    private val onGoBack: () -> Unit
) : ComponentContext by componentContext
{
    private val _game = GameCore(onServerClose = {onGoBack()})

    private val _table: MutableList<MutableList<Int>> = _game.table
    private val _serverInfo: MutableState<String> = _game.serverInfo
    private val _isActive: MutableState<Boolean> = _game.isActive

    val table: List<List<Int>> = _table
    val serverInfo = _serverInfo
    val isActive = _isActive

    init{
        goServer()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun goServer(){
        GlobalScope.launch{ _game.hostGame(port = 9999)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun tap(x: Int, y: Int){
        GlobalScope.launch {
            _game.sendAndWait(x, y)
        }
    }

    fun goBack(){
        _game.closeServer()
    }
}