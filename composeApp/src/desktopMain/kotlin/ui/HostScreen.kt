@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.HostScreenComponent

@Composable
fun HostScreen(
    component: HostScreenComponent) {
    val table = component.table
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Scaffold(
            topBar = {
                Row(modifier = Modifier.statusBarsPadding().fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    IconButton(
                        onClick = {
                            component.goBack()
                        }
                    )
                    {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = null)
                    }

                    Text(text = component.serverInfo.value, textAlign = TextAlign.Center)

                    IconButton(
                        enabled = false,
                        onClick = {}
                    )
                    {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = null, tint = Color.Transparent)
                    }
                }
            }
        ) {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                repeat(3) { row ->
                    Row {
                        repeat(3) { column ->
                            Button(enabled = (component.isActive.value && table[row][column] == -1)
                                ,modifier = Modifier.size(
                                100.dp
                            ),
                                onClick = {
                                    component.tap(row, column)
                                }) {

                                Text(
                                    if (table[row][column] == -1) " " else if(table[row][column] == 1) "X" else "0"

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}