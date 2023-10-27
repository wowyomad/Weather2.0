package by.bsuir.vadzim.weather20.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherInfo
import by.bsuir.vadzim.weather20.database.WeatherInfoDao
import by.bsuir.vadzim.weather20.database.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewmodel(
    private val dao: WeatherInfoDao
) : ViewModel() {
    private val _weatherInfo = MutableStateFlow(dao.getAllWeatherInfo())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
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
                _state.update {
                    it.copy(
                        isFavorite = !(it.isFavorite)
                    )
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
                _state.update {
                    it.copy(
                        isFavorite = true
                    )
                }
            }

            WeatherEvent.Unfavorite -> {
                _state.update {
                    it.copy(
                        isFavorite = false
                    )
                }
            }

            WeatherEvent.SaveWeather -> {
                val weatherType = state.value.weatherType

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

            is WeatherEvent.SetType -> {

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
