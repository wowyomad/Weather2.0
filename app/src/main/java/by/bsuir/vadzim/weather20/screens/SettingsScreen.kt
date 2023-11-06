package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import by.bsuir.vadzim.weather20.database.WeatherEvent

@Composable
fun SettingsScreen (navController: NavHostController, onEvent: (WeatherEvent) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(24.dp)
        .clickable(onClick = {
            onEvent(WeatherEvent.NukeTable)
        })
    ) {
        Text("Clear Database")
    }
}