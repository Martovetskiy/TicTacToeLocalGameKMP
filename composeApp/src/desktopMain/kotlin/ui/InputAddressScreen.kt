@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import components.InputAddressScreenComponent

@Composable
fun InputAddressScreen(component: InputAddressScreenComponent){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Scaffold(
            topBar = {
                Row(modifier = Modifier.statusBarsPadding().fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically){
                    IconButton(
                        onClick = {
                            component.goBack()
                        }
                    )
                    {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = null)
                    }

                }
            }
        ) {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(component.address.value, onValueChange = {
                    component.address.value = it
                })

                OutlinedTextField(component.port.value, onValueChange = {
                    component.port.value = it
                })

                Button(
                    enabled = component.port.value.toIntOrNull() != null,
                    onClick = {
                    component.goClient()
                })
                {
                    Text("Connect")
                }
            }
        }
    }
}