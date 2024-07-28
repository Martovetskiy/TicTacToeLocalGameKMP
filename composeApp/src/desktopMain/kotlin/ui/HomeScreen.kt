package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import components.HomeScreenComponent

@Composable
fun HomeScreen(component: HomeScreenComponent){

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Button(
            onClick = {component.goToHost()}
        )
        {
            Text("Go to host")
        }

        Button(
            onClick = {component.goToClient()}
        )
        {
            Text("Go to Client")
        }
    }

}