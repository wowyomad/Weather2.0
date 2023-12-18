package by.bsuir.vadzim.weather20.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import by.bsuir.vadzim.weather20.database.DbConstants

object WeatherMigrations {
    private val migration1to2 by lazy {
        object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE WeatherInfo ADD COLUMN description TEXT")
            }
        }
    }

    private val migration2to3 by lazy {
        object : Migration(2, 3) {
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
    }

    private val migration3to4 by lazy {
        object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                ALTER TABLE ${DbConstants.OLD_TABLE_NAME} RENAME TO ${DbConstants.TABLE_NAME}
            """.trimIndent()
                )
            }
        }
    }

    fun getMigrations(): Array<Migration> {
        return arrayOf<Migration>(migration1to2, migration2to3, migration3to4)
    }
}

