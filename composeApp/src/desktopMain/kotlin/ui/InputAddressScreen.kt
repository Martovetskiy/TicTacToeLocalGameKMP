@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import checkIPAddress
import components.InputAddressScreenComponent
import readSettings

@Composable
fun InputAddressScreen(component: InputAddressScreenComponent){
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

                    Text(text = component.textButton, textAlign = TextAlign.Center, color = theme.negativeText)

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
            Column (modifier = Modifier.background(color = theme.card, shape = RoundedCornerShape(16.dp)).width(400.dp).aspectRatio(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    isError = (!checkIPAddress(component.address.value) && component.address.value != "localhost") && component.address.value.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 32.dp, bottom = 4.dp).padding(horizontal = 16.dp).height(56.dp),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "IP адрес") },
                    shape = RoundedCornerShape(16.dp),
                    value = component.address.value,
                    onValueChange = {
                    component.address.value = it
                },
                  colors = TextFieldDefaults.outlinedTextFieldColors(
                      focusedBorderColor = theme.accent,
                      cursorColor = theme.negativeText,
                      textColor = theme.negativeText,
                      backgroundColor = theme.background.copy(alpha = 0.5f),
                      placeholderColor = theme.negativeText.copy(alpha = 0.6F)
                  )
                )

                OutlinedTextField(
                    isError = component.port.value.toIntOrNull() == null && component.port.value.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 4.dp, bottom = 16.dp).height(56.dp).padding(horizontal = 16.dp),
                    singleLine = true,
                    placeholder = { Text(text = "Порт") },
                    maxLines = 1,
                    shape = RoundedCornerShape(16.dp),
                    value = component.port.value,
                    onValueChange = {
                        component.port.value = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = theme.accent,
                        cursorColor = theme.negativeText,
                        textColor = theme.negativeText,
                        backgroundColor = theme.background.copy(alpha = 0.5f),
                        placeholderColor = theme.negativeText.copy(alpha = 0.6F)
                    ))

                Button(
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp).padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    enabled = component.port.value.toIntOrNull() != null && (checkIPAddress(component.address.value) || component.address.value == "localhost"),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = theme.accent,
                        contentColor = theme.negativeText,
                        disabledBackgroundColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    ),
                    onClick = {
                    component.goClient()
                })
                {
                    Text(component.textButton)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp).padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        component.address.value = "localhost"
                        component.port.value = "9999"
                        component.goClient()
                    })
                {
                    Text("Debug: ${component.textButton}")
                }
                Spacer(Modifier.height(32.dp))
            }
                }
        }
    }
