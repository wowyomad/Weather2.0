package by.bsuir.vadzim.weather20.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherInfo

@Composable
fun WeatherCard (weather: WeatherInfo, onEvent: (WeatherEvent) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {})
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
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
            }
        }
        
        Text("This is card for ${stringResource(id = weather.type.name)}")

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
            contentDescription = "Add to Favorite"
        )

    }
}