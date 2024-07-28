@file:Suppress("FunctionName")

package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.HomeScreenComponent

@Composable
fun HomeScreen(component: HomeScreenComponent){

    Column (
        modifier = Modifier.fillMaxSize().background(color = Gray4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column (
            modifier = Modifier.background(color = Light, shape = RoundedCornerShape(16.dp)).fillMaxWidth(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(modifier = Modifier.padding(bottom = 32.dp, top = 16.dp), text = "Крестки-Нолики", style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold))

            Button(
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
                modifier = Modifier.fillMaxWidth(.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { component.goToClient() }
            )
            {
                Text("Подключиться")
            }

            Text(modifier = Modifier.padding(16.dp), text = "Выполнено студентами группы ПиБ-232: Боднарь Степан и Тугбаева Эрика",
                textAlign = TextAlign.Center)
        }
    }

}