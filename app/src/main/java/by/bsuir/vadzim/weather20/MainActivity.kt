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
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import by.bsuir.vadzim.weather20.database.WeatherInfoDatabase
import by.bsuir.vadzim.weather20.screens.MainScreen
import by.bsuir.vadzim.weather20.screens.MainViewmodel
import by.bsuir.vadzim.weather20.ui.theme.Weather20Theme

class MainActivity : ComponentActivity() {

    private val migration1to2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE WeatherInfo ADD COLUMN description TEXT")
        }
    }

    private val migration2to3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("UPDATE WeatherInfo SET description = \" \" WHERE description is NULL")
            db.execSQL(
                """
                    CREATE TABLE IF NOT EXISTS `WeatherInfo_temp` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL, `description` TEXT NOT NULL)
                    """.trimIndent()
            )
            db.execSQL(
                """
                    INSERT INTO WeatherInfo_temp (id, type, isFavorite, description)
                    SELECT id, type, isFavorite, description FROM WeatherInfo;
                """.trimIndent()
            )
            db.execSQL("DROP TABLE WeatherInfo")
            db.execSQL("ALTER TABLE WeatherInfo_temp RENAME TO WeatherInfo")
        }

    }
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = WeatherInfoDatabase::class.java,
            name = "weather"
        )
            .addMigrations(migration1to2, migration2to3)
            .build()
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
                val state by viewModel.state.collectAsState()
                MainScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

