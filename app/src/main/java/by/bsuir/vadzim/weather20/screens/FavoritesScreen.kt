package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherGroup
import by.bsuir.vadzim.weather20.database.WeatherState

@Composable
fun FavoritesScreen (onEvent: (WeatherEvent) -> Unit, state: WeatherState) {
    onEvent(WeatherEvent.SetGroup(WeatherGroup.FAVORITE))

    LazyColumn (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(items = state.weatherInfoItems) { weather ->
            WeatherCard(weather = weather, onEvent = onEvent)

        }
    }
}