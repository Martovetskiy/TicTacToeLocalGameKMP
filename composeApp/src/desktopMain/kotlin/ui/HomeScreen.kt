@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import changeTheme
import components.HomeScreenComponent
import localtictactoe.composeapp.generated.resources.Res
import localtictactoe.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import readSettings

@Composable
fun HomeScreen(component: HomeScreenComponent){

    val settings = remember{ mutableStateOf(readSettings()) }
    val theme = settings.value.theme
    val nickname = component.nickname

    val focus = LocalFocusManager.current

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .clickable(
                onClick = {
                    focus.clearFocus()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(modifier = Modifier.fillMaxSize(),
            painter = painterResource(theme.imageBack),
            contentDescription = null,
            contentScale = ContentScale.FillBounds)

        Column (
            modifier = Modifier
                .background(
                    color = Light.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .width(500.dp)
                .aspectRatio(1f)
                .border(width = 2.dp, color = theme.card, shape = RoundedCornerShape(16.dp)),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(modifier = Modifier.padding(top = 16.dp), text = "Крестки-Нолики", style = TextStyle(fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = theme.text))

            Image(modifier = Modifier.size(120.dp), painter = painterResource(Res.drawable.logo), contentDescription = null)

            Button(
                enabled = component.nickname.value.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = theme.butColor,
                    contentColor = theme.negativeText
                ),
                modifier = Modifier.fillMaxWidth(.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = {component.goToHost()}
            )
            {
                Text("Создать сервер")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                enabled = component.nickname.value.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = theme.butColor,
                    contentColor = theme.negativeText
                ),
                modifier = Modifier.fillMaxWidth(.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { component.goToClient() }
            )
            {
                Text("Подключиться")
            }

            Text(modifier = Modifier.padding(16.dp), text = "Выполнено студентами группы ПиБ-232: Боднарь Степан и Тугбаева Эрика",
                textAlign = TextAlign.Center,
                color = theme.text)

        }


            TextButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = theme.accent,
                    contentColor = theme.negativeText
                ),
                onClick = {
                    settings.value = changeTheme(theme)
            }){
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                Text(text = theme.name)
                }
            }

        OutlinedTextField(
            isError = component.nickname.value.isEmpty(),
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .align(Alignment.TopStart)
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .onPreviewKeyEvent {
                    if (it.key == Key.Enter){
                        focus.clearFocus()
                        true
                    }
                    else{
                        false
                    }
                },
            singleLine = true,
            placeholder = { Text(text = "Никнейм") },
            maxLines = 1,
            shape = RoundedCornerShape(16.dp),
            value = nickname.value,
            onValueChange = {
                component.nickname.value= it
                component.saveNick()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = theme.butColor,
                cursorColor = theme.negativeText,
                textColor = theme.negativeText,
                backgroundColor = theme.background.copy(alpha = 0.5f),
                placeholderColor = theme.negativeText.copy(alpha = 0.6F)
            )
        )


    }

}



