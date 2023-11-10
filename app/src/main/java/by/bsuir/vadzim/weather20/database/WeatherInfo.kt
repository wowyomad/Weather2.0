package by.bsuir.vadzim.weather20.database

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import by.bsuir.vadzim.weather20.R
import java.util.Date

@Entity
data class WeatherInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: WeatherType = WeatherType.Sunny,
    val isFavorite: Boolean = false,
    val description: String = ""
) {
    constructor(copy: WeatherInfo) : this(
        id = copy.id,
        type = copy.type,
        isFavorite = copy.isFavorite,
        description = copy.description
    )
}

sealed class WeatherType (
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object Cloudy : WeatherType(
        name = R.string.weather_cloudy_name,
        icon = R.drawable.cloudy,
    )

    object Foggy : WeatherType(
        name = R.string.weather_foggy_name,
        icon = R.drawable.foggy
    )

    object PartlyCloudy : WeatherType(
        name = R.string.weather_partly_cloudy_name,
        icon = R.drawable.partly_cloudy
    )

    object Rainy : WeatherType(
        name = R.string.weather_rainy_name,
        icon = R.drawable.rainy
    )

    object Snowy : WeatherType(
        name = R.string.weather_snowy_name,
        icon = R.drawable.snowy
    )

    object Sunny : WeatherType(
        name = R.string.weather_sunny_name,
        icon = R.drawable.sunny
    )

    object Thunder : WeatherType(
        name = R.string.weather_thunder_name,
        icon = R.drawable.thunder
    )

    object Windy : WeatherType(
        name = R.string.weather_windy_name,
        icon = R.drawable.windy
    )

    companion object {
        fun listOf() : List<WeatherType> {
            return listOf<WeatherType>(
                Cloudy, Foggy, PartlyCloudy, Rainy, Snowy, Sunny, Thunder, Windy
            )
        }
    }
}

class WeatherTypeConverter {
    @TypeConverter
    fun fromWeatherType(weatherType: WeatherType): String {
        return weatherType.javaClass.simpleName
    }
    @TypeConverter
    fun toWeatherType(weatherTypeString: String): WeatherType {
        return when (weatherTypeString) {
            "Cloudy" -> WeatherType.Cloudy
            "Foggy" -> WeatherType.Foggy
            "PartlyCloudy" -> WeatherType.PartlyCloudy
            "Rainy" -> WeatherType.Rainy
            "Snowy" -> WeatherType.Snowy
            "Sunny" -> WeatherType.Sunny
            "Thunder" -> WeatherType.Thunder
            "Windy" -> WeatherType.Windy
            else -> throw IllegalArgumentException("Unsupported WeatherType: $weatherTypeString")
        }
    }
}

enum class WeatherGroup {
    ALL,
    FAVORITE
}
