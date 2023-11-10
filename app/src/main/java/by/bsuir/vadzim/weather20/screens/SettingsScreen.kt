package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import by.bsuir.vadzim.weather20.database.WeatherEvent

@Composable
fun SettingsScreen(navController: NavHostController, onEvent: (WeatherEvent) -> Unit) {


    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Button(onClick = {
            onEvent(WeatherEvent.NukeTable)
        }) {
            Text("Clear Database")

        }
    }

}