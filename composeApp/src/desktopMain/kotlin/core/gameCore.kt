package core

import androidx.compose.runtime.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

class GameCore (
    private val onServerClose: () -> Unit = {},
    private val onClientClose: () -> Unit = {},
) {
    private val you = mutableIntStateOf(1)
    private val opponent = mutableIntStateOf(0)
    private val _winner: MutableIntState = mutableIntStateOf(-1)

    private lateinit var server: ServerSocket
    private lateinit var client: Socket
    private lateinit var sender : PrintWriter
    private lateinit var reader : BufferedReader

    val table: MutableList<MutableList<Int>> = mutableStateListOf(
        mutableStateListOf(-1, -1, -1),
        mutableStateListOf(-1, -1, -1),
        mutableStateListOf(-1, -1, -1),
    )

    val serverInfo: MutableState<String> = mutableStateOf("Starting server...")
    val isActive: MutableState<Boolean> = mutableStateOf(false)


    private fun checkWinner():Boolean {
        for (row in 0..2) {
            if (table[row][0] == table[row][1] && table[row][1] == table[row][2] && table[row][2] != -1)
            {
                _winner.value = table[row][0]
                isActive.value = false
                return true
            }
        }
        for (col in 0..2) {
            if (table[0][col] == table[1][col] && table[1][col] == table[2][col] && table[2][col] != -1)
            {
                _winner.value = table[2][col]
                isActive.value = false
                return true
            }
        }
        if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[2][2] != -1){
            _winner.value = table[0][0]
            isActive.value = false
            return true
        }
        if (table[0][2] == table[1][1] && table[1][1] == table[2][0] && table[2][0] != -1){
            _winner.value = table[0][2]
            isActive.value = false
            return true
        }
        return false
    }

    private fun initClient(){
        you.value = 0
        opponent.value = 1
        isActive.value = false
        val receive = reader.readLine().split(", ")
        changeTable(receive[0].toInt(), receive[1].toInt(), opponent.value)
        isActive.value = true
    }

    private fun changeTable(x: Int, y: Int, figure: Int) {
        table[x][y] = figure
        println(table)
    }

    fun hostGame(host: String = "localhost", port: Int){
        val address = InetAddress.getByName(host)
        try {
            server = ServerSocket(port, 50, address)
        }
        catch (e: IOException){
            onServerClose()
        }
        serverInfo.value = "Wait player..."
        client = server.accept()
        serverInfo.value = "TicTacToe"

        sender = PrintWriter(client.getOutputStream(), true)
        reader = BufferedReader(InputStreamReader(client.getInputStream()))

        you.value = 1
        opponent.value = 0
        isActive.value = true
    }

    fun connectGame(host: String, port: Int) {
        try {
            client = Socket(host, port)
        }
        catch (e: Exception) {
            onClientClose()
        }
        sender = PrintWriter(client.getOutputStream(), true)
        reader = BufferedReader(InputStreamReader(client.getInputStream()))

        initClient()
    }

    fun sendAndWait(x: Int, y: Int){
        sender.println("$x, $y")
        changeTable(x, y, you.value)
        if (checkWinner()){
           serverInfo.value = "Winner: ${_winner.value}"
            return
        }
        isActive.value = false
        val receive = reader.readLine().split(", ")
        changeTable(receive[0].toInt(), receive[1].toInt(), opponent.value)
        if (checkWinner()){
            serverInfo.value = "Winner: ${_winner.value}"
            return
        }
        isActive.value=true
    }

    fun closeServer(){
        server.close()
        onServerClose()

    }

    fun closeClient(){
        client.close()
        onClientClose()
    }

}