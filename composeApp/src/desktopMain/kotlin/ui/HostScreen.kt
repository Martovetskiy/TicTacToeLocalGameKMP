@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.HostScreenComponent
import resources.icons.Circle
import resources.icons.Cross
import readSettings

@Composable
fun HostScreen(
    component: HostScreenComponent,
    ) {
    val table = component.table
    
    val settings = remember{ mutableStateOf(readSettings()) }
    val theme = settings.value.theme
    
    Scaffold(
        backgroundColor = theme.background,
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
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = theme.negativeText)
                }

                Text(text = component.serverInfo.value, textAlign = TextAlign.Center, color = theme.negativeText)

                IconButton(
                    enabled = false,
                    onClick = {}
                )
                {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = Color.Transparent)
                }
            }
        }
    ) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = component.moveInfo.value,
                color = theme.negativeText
            )
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            repeat(3) { row ->
                Row {
                    repeat(3) { column ->
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = theme.card,
                                disabledBackgroundColor = if(
                                    ((component.winnerComb.value.first.first == row && component.winnerComb.value.first.second == column) ||
                                    (component.winnerComb.value.second.first == row && component.winnerComb.value.second.second == column) ||
                                    (component.winnerComb.value.third.first == row && component.winnerComb.value.third.second == column)) &&
                                    component.isGameOver.value
                                    )
                                    Color.Green else
                                    theme.card
                            ),
                            enabled = (component.isActive.value && table[row][column] == -1),
                            modifier = Modifier
                                .size(100.dp)
                                .padding(4.dp),
                            onClick = {
                                component.tap(row, column)
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(if (table[row][column] == 1) 50.dp else 68.dp),
                                imageVector = if (table[row][column] == 1) Cross else Circle,
                                contentDescription = null,
                                tint = if (table[row][column] != -1) theme.accent else Color.Transparent)
                        }
                    }
                }
            }
        }
            }
    }

}