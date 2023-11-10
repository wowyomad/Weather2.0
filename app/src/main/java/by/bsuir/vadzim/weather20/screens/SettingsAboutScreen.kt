package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun SettingsAboutScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text("This is about screen", fontSize = 32.sp)
    }
}