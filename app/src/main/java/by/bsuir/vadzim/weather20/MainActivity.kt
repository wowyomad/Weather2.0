package by.bsuir.vadzim.weather20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import by.bsuir.vadzim.weather20.database.WeatherInfoDatabase
import by.bsuir.vadzim.weather20.screens.MainScreen
import by.bsuir.vadzim.weather20.screens.MainViewmodel
import by.bsuir.vadzim.weather20.ui.theme.Weather20Theme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = WeatherInfoDatabase::class.java,
            name = "weather.db"
        ).build()
    }


    private val viewModel by viewModels<MainViewmodel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewmodel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Weather20Theme {
                MainScreen(viewModel)
            }
        }
    }
}

