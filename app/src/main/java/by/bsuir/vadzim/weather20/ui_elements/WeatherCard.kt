package by.bsuir.vadzim.weather20.ui_elements

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherCard(weather: WeatherInfo, onEvent: (WeatherEvent) -> Unit) {
    val shape = RoundedCornerShape(12.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .combinedClickable(
                onClick = { onEvent(WeatherEvent.ShowEditDialog(weather)) },
                onLongClick = { onEvent(WeatherEvent.DeleteWeather(weather)) },
                onDoubleClick = {}
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1.25f)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(weather.type.icon),
                contentDescription = null
            )
            Row {
                Text(text = stringResource(id = weather.type.name), fontSize = 12.sp)
            }
        }

        Text(
            text = weather.description,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.Top)
                .padding(top = 12.dp)
        )
        val interactionSource = remember { MutableInteractionSource() }
        val pressed by interactionSource.collectIsPressedAsState()
        IconButton(
            interactionSource = interactionSource,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            onClick = {
                onEvent(WeatherEvent.Favorite(weather))
            }) {
            val scale by animateFloatAsState(
                targetValue = if (weather.isFavorite) 1.2f else 1f
            )
            val animationSpec = tween<Float>(durationMillis = 300, easing = LinearOutSlowInEasing)


            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                imageVector =
                if (!weather.isFavorite) Icons.Default.FavoriteBorder
                else Icons.Default.Favorite,
                contentDescription = "Add to Favorite",
                colorFilter = ColorFilter.tint(
                    if(weather.isFavorite) Color.Red
                    else if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )

        }

    }
}