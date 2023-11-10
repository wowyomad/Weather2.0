package by.bsuir.vadzim.weather20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {
    @Upsert
    suspend fun upsert(weatherInfo: WeatherInfo)
    @Delete
    suspend fun delete(weatherInfo: WeatherInfo)
    @Query("SELECT * FROM WeatherInfo")
    fun getAllWeatherInfo(): Flow<List<WeatherInfo>>

    @Query("DELETE FROM WeatherInfo")
    fun  nukeTable()

    @Query("UPDATE WeatherInfo SET isFavorite =:isFavorite WHERE id = :id")
    fun setFavorite(isFavorite: Boolean, id: Int)

    @Query("SELECT * FROM WeatherInfo WHERE isFavorite = 1")
    fun getFavoritesWeatherInfo(): Flow<List<WeatherInfo>>
}