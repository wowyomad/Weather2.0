package by.bsuir.vadzim.weather20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {
    @Upsert
    suspend fun upsert(weather: Weather)
    @Delete
    suspend fun delete(weather: Weather)
    @Query("SELECT * FROM Weather")
    fun getAllWeatherInfo(): Flow<List<Weather>>

    @Query("DELETE FROM Weather")
    suspend fun  nukeTable()

    @Query("UPDATE Weather SET isFavorite =:isFavorite WHERE id = :id")
    suspend fun setFavorite(isFavorite: Boolean, id: Int)

    @Query("SELECT * FROM Weather WHERE isFavorite = 1")
    fun getFavoritesWeatherInfo(): Flow<List<Weather>>

}