package by.bsuir.vadzim.weather20.ui_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.bsuir.vadzim.weather20.R
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.Weather
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.database.WeatherType


fun WeatherDialog(
    title: String = "",
    weather: Weather? = null
) {

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddWeatherDialog(
    title: String = "",
    weather: Weather? = null,
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit,
    modifier: Modifier = Modifier,
) {


    val context = LocalContext.current
    onEvent(WeatherEvent.SetContext(context))

    AlertDialog(
        title = { Text(text = title) },
        onDismissRequest = {
            onEvent(WeatherEvent.HideDialog)
        },
        confirmButton = {
            Button(onClick = { onEvent(WeatherEvent.SaveWeather(weather, context)) }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        },
        text = {
            val items = WeatherType.listOf()
            var expanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableIntStateOf(0) }

            Column {
                TextField(
                    value = state.weatherDescription,
                    onValueChange = {
                        onEvent(WeatherEvent.SetDescription(it))
                    },
                    label = { Text(stringResource(id = R.string.weather_description_type)) }
                )

                Box(
                    modifier = Modifier
                        .clickable(onClick = { expanded = true })
                ) {

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            value = stringResource(id = state.weatherType.name),
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(id = R.string.label_weather_type)) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            items.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Image(
                                                modifier = Modifier.padding(horizontal = 16.dp),
                                                painter = painterResource(id = item.icon),
                                                contentDescription = null
                                            )

                                            Text(
                                                modifier = Modifier.padding(horizontal = 24.dp),
                                                text = stringResource(id = item.name)
                                            )
                                        }

                                    }, onClick = {
                                        onEvent(WeatherEvent.SetType(item))
                                        selectedIndex = index
                                        expanded = false

                                    })
                            }
                        }
                    }
                }
            }


        })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WeatherRemoveDialog(weather: Weather?, onEvent: (WeatherEvent) -> Unit) {

    AlertDialog(
        title = { Text("Remove weather") },
        onDismissRequest = {
            onEvent(WeatherEvent.HideRemoveDialog)
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(WeatherEvent.DeleteWeather(weather))
                    onEvent(WeatherEvent.HideRemoveDialog)
                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }

        }
    )
}