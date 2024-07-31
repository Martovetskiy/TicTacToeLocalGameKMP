@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import checkIPAddress
import components.InputAddressScreenComponent

@Composable
fun InputAddressScreen(component: InputAddressScreenComponent){
    val themeColor = BloodAndWaterTheme()
        Scaffold(
            backgroundColor = themeColor.BackgroundColor,
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
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                    }

                    Text(text = component.textButton, textAlign = TextAlign.Center)

                    IconButton(
                        enabled = false,
                        onClick = {}
                    )
                    {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = Color.Transparent)
                    }

                }
            }
        ) { innerPadding ->
            Column (
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
            Column (modifier = Modifier.background(color = themeColor.CardColor, shape = RoundedCornerShape(16.dp)).fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    isError = (!checkIPAddress(component.address.value) && component.address.value != "localhost") && component.address.value.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(.6F)
                        .padding(top = 32.dp, bottom = 4.dp).height(56.dp),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "IP адрес") },
                    shape = RoundedCornerShape(16.dp),
                    value = component.address.value,
                    onValueChange = {
                    component.address.value = it
                })

                OutlinedTextField(
                    isError = component.port.value.toIntOrNull() == null && component.port.value.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(.6F)
                        .padding(top = 4.dp, bottom = 16.dp).height(56.dp),
                    singleLine = true,
                    placeholder = { Text(text = "Порт") },
                    maxLines = 1,
                    shape = RoundedCornerShape(16.dp),
                    value = component.port.value,
                    onValueChange = {
                        component.port.value = it
                    })

                Button(
                    modifier = Modifier.fillMaxWidth(.6f)
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    enabled = component.port.value.toIntOrNull() != null && (checkIPAddress(component.address.value) || component.address.value == "localhost"),
                    onClick = {
                    component.goClient()
                })
                {
                    Text(component.textButton)
                }
                Spacer(Modifier.height(32.dp))
            }
                }
        }
    }
