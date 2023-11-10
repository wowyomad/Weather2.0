package by.bsuir.vadzim.weather20.database

data class WeatherState(
    val weatherInfoItems: List<WeatherInfo> = emptyList(),
    val weatherType: WeatherType = WeatherType.Cloudy,
    val weatherId: Int = 0,
    val weatherDescription: String = "",
    val isAddingInfo: Boolean = false,
    val weatherIsFavorite: Boolean = false,
    val isRefreshing: Boolean = false,
    val weatherGroup: WeatherGroup = WeatherGroup.ALL
)