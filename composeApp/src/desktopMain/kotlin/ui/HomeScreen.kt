@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Box (
        modifier = Modifier.fillMaxSize().background(color = theme.background),
        contentAlignment = Alignment.Center
    ) {

        Column (
            modifier = Modifier.background(color = theme.card, shape = RoundedCornerShape(16.dp)).width(400.dp).aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(modifier = Modifier.padding(top = 16.dp), text = "Крестки-Нолики", style = TextStyle(fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = theme.text))

            Image(modifier = Modifier.size(120.dp), painter = painterResource(Res.drawable.logo), contentDescription = null)

            Button(
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

    }

}



