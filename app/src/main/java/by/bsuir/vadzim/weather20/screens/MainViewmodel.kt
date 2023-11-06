package by.bsuir.vadzim.weather20.screens

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherInfo
import by.bsuir.vadzim.weather20.database.WeatherInfoDao
import by.bsuir.vadzim.weather20.database.WeatherState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewmodel(
    private val dao: WeatherInfoDao
) : ViewModel() {
    private val _weatherInfoItems = MutableStateFlow(dao.getAllWeatherInfo())
        .flatMapLatest {
            dao.getAllWeatherInfo()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList<WeatherInfo>()
        )

    private val _state = MutableStateFlow(WeatherState())
    val state = combine(_state, _weatherInfoItems) {
        state, weatherInfoItems ->
            state.copy(
                weatherInfoItems = weatherInfoItems
            )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WeatherState()
    )

    fun InsertWeatherCard(weatherInfo: WeatherInfo) {
        viewModelScope.launch {
            dao.insert(weatherInfo)
        }
    }

    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.DeleteWeather -> {
                viewModelScope.launch {
                    dao.delete(event.weather)
                }
            }

            is WeatherEvent.Favorite -> {
                viewModelScope.launch {
                    dao.setFavorite(!event.weatherItem.isFavorite, event.weatherItem.id)
                }
            }

            WeatherEvent.HideDialog -> {
                _state.update {
                    it.copy(isAddingInfo = false)
                }
            }

            WeatherEvent.ShowDialog -> {
                _state.update {
                    it.copy(isAddingInfo = true)
                }
            }

            WeatherEvent.MakeFavorite -> {

            }

            WeatherEvent.Unfavorite -> {

            }

            WeatherEvent.SaveWeather -> {
                val weatherType = state.value.weatherType
                println(weatherType.name)
                println(weatherType.icon)

                val weather = WeatherInfo(
                    type = weatherType
                )

                viewModelScope.launch {
                    dao.insert(weather)
                }

                _state.update {
                    it.copy(
                        isAddingInfo = false
                    )
                }

                onEvent(WeatherEvent.HideDialog)

            }

            WeatherEvent.NukeTable -> {
                viewModelScope.launch {
                    dao.nukeTable()
                }
            }

            is WeatherEvent.SetType -> {
                _state.update {
                    it.copy(
                        weatherType = event.weatherType
                    )
                }
            }


        }
    }

    fun AddToFavorite(other: WeatherInfo) {
        InsertWeatherCard(
            WeatherInfo(
                other.id, other.type, true
            )
        )
    }

    fun RemoveFromFavorite(other: WeatherInfo) {
        InsertWeatherCard(
            WeatherInfo(
                other.id, other.type, false
            )
        )
    }
}
