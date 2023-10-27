package by.bsuir.vadzim.weather20.screens

import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import by.bsuir.vadzim.weather20.R
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.database.WeatherType
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddWeatherDialog (
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf<WeatherType>(WeatherType.Cloudy, WeatherType.Rainy, WeatherType.Sunny)
    var expanded by remember { mutableStateOf(false)}
    AlertDialog(
        title = { Text(text = "Add weather")},
        onDismissRequest = {

        },
        confirmButton = {

        },
        text = {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
                TextField(
                    value = state.weatherType.toString(),
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.label_weather_type))},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    })
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    items.forEach {selectionOption ->
                        DropdownMenuItem(
                            text = {
                                   Icon(
                                       imageVector = ImageVector.vectorResource(id = state.weatherType.icon),
                                       contentDescription = null)
                                Text(text = stringResource(id = state.weatherType.name))
                            }, onClick = {
                                onEvent(WeatherEvent.SetType(selectionOption))
                                expanded = false

                            })
                    }   
                }
            }

        }
    )


}
