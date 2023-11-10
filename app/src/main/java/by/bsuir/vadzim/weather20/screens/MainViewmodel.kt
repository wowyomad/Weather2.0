package by.bsuir.vadzim.weather20.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherGroup
import by.bsuir.vadzim.weather20.database.WeatherInfo
import by.bsuir.vadzim.weather20.database.WeatherInfoDao
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.database.WeatherType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewmodel(
    private val dao: WeatherInfoDao,
) : ViewModel() {

    private val _weatherGroup = MutableStateFlow(WeatherGroup.ALL)

    private val _context: MutableStateFlow<Context?> = MutableStateFlow<Context?>(null)
    public val context = _context.asStateFlow()

    public fun setContext(context: Context) {
        _context.update {
            context
        }
    }

    public fun removeContext() {
        _context.update {
            null
        }
    }


    private val _isRefreshing = MutableStateFlow(false)

    private val _weatherInfoItems = _weatherGroup
        .flatMapLatest { weatherGroup ->
            when (weatherGroup) {
                WeatherGroup.ALL -> dao.getAllWeatherInfo()
                WeatherGroup.FAVORITE -> dao.getFavoritesWeatherInfo()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList<WeatherInfo>()
        )

    private val _state = MutableStateFlow(WeatherState())
    val state = combine(
        _state,
        _weatherInfoItems,
        _isRefreshing,
        _weatherGroup
    ) { state, weatherInfoItems, isRefreshing, weatherGroup ->
        state.copy(
            isRefreshing = isRefreshing,
            weatherInfoItems = weatherInfoItems,
            weatherGroup = weatherGroup
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WeatherState()
    )


    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            dao.getAllWeatherInfo()
            delay(1000)
            _isRefreshing.emit(false)
        }

    }

    fun onEvent(event: WeatherEvent): Boolean {
        when (event) {
            is WeatherEvent.DeleteWeather -> {
                viewModelScope.launch {
                    dao.delete(event.weather)
                }
                val context = event.context?: context.value
                Log.d("DeleteWeather", context.toString())
                if(context != null) {
                    Toast.makeText(context, "Removed user o_o", Toast.LENGTH_SHORT).show()
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

            is WeatherEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingInfo = true
                    )
                }
            }

            is WeatherEvent.ShowEditDialog -> {
                Log.d("ShowEditDialog", "${event.weather}")
                _state.update {
                    it.copy(
                        weatherId = event.weather.id,
                        weatherDescription = event.weather.description,
                        weatherType = event.weather.type,
                        weatherIsFavorite = event.weather.isFavorite
                    )
                }
                onEvent(WeatherEvent.ShowDialog)
            }

            WeatherEvent.MakeFavorite -> {

            }

            WeatherEvent.Unfavorite -> {

            }

            is WeatherEvent.SaveWeather -> {
                val weather: WeatherInfo
                val success = true
                weather = WeatherInfo(
                    id = state.value.weatherId,
                    type = state.value.weatherType,
                    description = state.value.weatherDescription,
                    isFavorite = state.value.weatherIsFavorite
                )

                viewModelScope.launch {
                    dao.upsert(weather)
                }

                val context = event.context?: context.value
                if(context != null) {
                    Toast.makeText(context, "Success :DDDD", Toast.LENGTH_SHORT).show()
                }


                _state.update {
                    it.copy(
                        isAddingInfo = false
                    )
                }
                onEvent(WeatherEvent.ClearWeather)
                onEvent(WeatherEvent.HideDialog)

            }

            is WeatherEvent.NukeTable -> {
                viewModelScope.launch {
                    dao.nukeTable()
                }

                val context = event.context?: context.value
                if(context != null) {
                    Toast.makeText(context, "Table Nuked :o", Toast.LENGTH_SHORT).show()
                }
            }

            is WeatherEvent.SetType -> {
                _state.update {
                    it.copy(
                        weatherType = event.weatherType
                    )
                }
            }

            is WeatherEvent.SetGroup -> {
                if (_weatherGroup.value != event.weatherGroup) {
                    _weatherGroup.value = event.weatherGroup
                }
            }

            WeatherEvent.RefreshData -> {
                refreshData()
            }

            is WeatherEvent.SetDescription -> {
                _state.update {
                    it.copy(
                        weatherDescription = event.description
                    )
                }
            }


            is WeatherEvent.SetWeather -> {
                throw NotImplementedError("SetWeather not yet ready")
            }

            WeatherEvent.ClearWeather -> {
                _state.update {
                    it.copy(
                        weatherId = 0,
                        weatherType = WeatherType.Sunny,
                        weatherDescription = "",
                        weatherIsFavorite = false
                    )
                }
            }

            is WeatherEvent.SetContext -> {
                viewModelScope.launch {
                    setContext(event.context)
                }
            }
        }
    return false
    }

}
