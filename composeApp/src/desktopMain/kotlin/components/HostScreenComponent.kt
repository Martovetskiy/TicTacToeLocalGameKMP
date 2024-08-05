package components

import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.ComponentContext
import core.GameCore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HostScreenComponent(
    componentContext: ComponentContext,
    private val address: String,
    private val port: Int,
    private val onGoBack: () -> Unit
) : ComponentContext by componentContext
{
    private val _game = GameCore(onServerClose = {onGoBack()})

    private val _table: MutableList<MutableList<Int>> = _game.table
    private val _serverInfo: MutableState<String> = _game.serverInfo
    private val _moveInfo: MutableState<String> = _game.moveLabel
    private val _isActive: MutableState<Boolean> = _game.isActive
    private val _isGameOver: MutableState<Boolean> = _game.isGameOver
    private val _winnerComb: MutableState<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>> = _game.winnerComb

    val table: List<List<Int>> = _table
    val serverInfo = _serverInfo
    val moveInfo = _moveInfo
    val isActive = _isActive
    val isGameOver = _isGameOver
    val winnerComb = _winnerComb

    init{
        goServer()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun goServer(){
        GlobalScope.launch{ _game.hostGame(host = address, port = port)
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