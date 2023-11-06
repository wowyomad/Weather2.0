package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherState

@Composable
fun HomeScreen(state: WeatherState, onEvent: (WeatherEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isAddingInfo) {
            AddWeatherDialog(state = state, onEvent = onEvent)
        }
        LazyColumn {
            itemsIndexed(items = state.weatherInfoItems) { index, weather ->
                Row(
                    modifier = Modifier.fillMaxWidth().clickable(onClick = {})
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(weather.type.icon),
                            contentDescription = null
                        )
                        Row() {
                            Text(text = stringResource(id = weather.type.name))
                            Text(text = weather.type.icon.toString())
                        }
                    }

                    Icon(
                        modifier = Modifier
                            .clickable(onClick = {
                            onEvent(WeatherEvent.Favorite(weather))
                        })
                            .align(Alignment.CenterVertically)
                            .padding(end = 8.dp),
                        imageVector =
                        if (!weather.isFavorite) Icons.Default.FavoriteBorder
                        else Icons.Default.Favorite,
                        contentDescription = "Add to Favorite")

                }

            }
        }


    }
}