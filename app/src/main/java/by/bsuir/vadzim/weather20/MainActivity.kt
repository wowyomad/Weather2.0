package by.bsuir.vadzim.weather20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import by.bsuir.vadzim.weather20.database.WeatherInfoDatabase
import by.bsuir.vadzim.weather20.database.migrations.WeatherMigrations
import by.bsuir.vadzim.weather20.screens.MainScreen
import by.bsuir.vadzim.weather20.screens.MainViewmodel
import by.bsuir.vadzim.weather20.ui.theme.Weather20Theme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = WeatherInfoDatabase::class.java,
            name = "weather"
        )
            .addMigrations(migrations = WeatherMigrations.ALL_MIGRATIONS)
            .build()
    }


    private val viewModel by viewModels<MainViewmodel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return modelClass.getConstructor(WeatherInfoDatabase::class.java)
                        .newInstance(db.dao)
                    /* this one is cleaner but gives unchecked cast warning*/
//                   return MainViewmodel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Weather20Theme {
                val state by viewModel.state.collectAsState()
                MainScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

