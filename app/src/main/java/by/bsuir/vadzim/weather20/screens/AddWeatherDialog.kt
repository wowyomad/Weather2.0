package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.LineHeightStyle
import by.bsuir.vadzim.weather20.R
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.database.WeatherType
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddWeatherDialog(
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    AlertDialog(
        title = { Text(text = "Add weather") },
        onDismissRequest = {
            onEvent(WeatherEvent.HideDialog)
        },
        confirmButton = {
            Button(onClick = { onEvent(WeatherEvent.SaveWeather) }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        },
        text = {
            val items = WeatherType.listOf()
            var expanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                Text(
                    stringResource(items[selectedIndex].name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = true })
                        .background(
                            Color.Gray
                        )
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = {
                                Column() {
                                    Image(
                                        painter = painterResource(id = item.icon),
                                        contentDescription = null
                                    )
                                    Text(text = stringResource(id = item.name))
                                }

                            }, onClick = {
                                onEvent(WeatherEvent.SetType(item))
                                selectedIndex = index
                                expanded = false

                            })
                    }

                }
            }
        })
}