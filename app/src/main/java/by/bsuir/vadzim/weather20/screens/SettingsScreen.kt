package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.bsuir.vadzim.weather20.R
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.navigation.Screen
import by.bsuir.vadzim.weather20.ui_elements.RoundedCard

@Composable
fun SettingsScreen(navController: NavHostController, onEvent: (WeatherEvent) -> Unit) {
    val context = LocalContext.current
    Surface {
        Column {
            Spacer(modifier = Modifier.padding(24.dp))
            RoundedCard(
                text = stringResource(id = R.string.settings_clearDatabase),
                icon = Icons.Default.Delete,
                onClick = {
                    onEvent(WeatherEvent.NukeTable(context))
                })
            Spacer(modifier = Modifier.padding(24.dp))
            RoundedCard(
                text = stringResource(id = R.string.settings_about_title),
                icon  = Icons.Default.Info,
                onClick = {
                    navController.navigate(Screen.Settings.About.route) {

                    }
                }
            )
        }

    }


}