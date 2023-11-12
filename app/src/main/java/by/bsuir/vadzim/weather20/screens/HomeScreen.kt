package by.bsuir.vadzim.weather20.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherGroup
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.ui_elements.AddWeatherDialog
import by.bsuir.vadzim.weather20.ui_elements.WeatherCard
import by.bsuir.vadzim.weather20.ui_elements.WeatherRemoveDialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(state: WeatherState, onEvent: (WeatherEvent) -> Unit, paddingValues: PaddingValues = PaddingValues()) {

    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    Log.d("HomeScreen", context.toString())
    onEvent(WeatherEvent.SetContext(context))


    onEvent(WeatherEvent.SetGroup(WeatherGroup.ALL))

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            onEvent(WeatherEvent.RefreshData)
        })


    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)
    ) {
        if (state.isAddingInfo) {
            AddWeatherDialog(weather = null, state = state, onEvent = onEvent)
        }

        if(state.isRemoving) {
            WeatherRemoveDialog(weather = null, onEvent = onEvent)
        }

        LazyColumn(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp,
                top = 12.dp
            ),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.weatherItems) { weather ->
                WeatherCard(
                    weather = weather,
                    onEvent = onEvent,
                    onClick = {
                        onEvent(WeatherEvent.ShowEditDialog(weather))
                    },
                    onLongClick = {
                        onEvent(WeatherEvent.ShowRemoveDialog(weather))
                    },
                    onDoubleClick = {
                        onEvent(WeatherEvent.Favorite(weather))
                    }
                    )
            }
            item {
                Spacer(modifier = Modifier.padding(40.dp))
            }
        }

        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
