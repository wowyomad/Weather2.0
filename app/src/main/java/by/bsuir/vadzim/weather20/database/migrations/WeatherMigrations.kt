package by.bsuir.vadzim.weather20.database.migrations

import androidx.room.migration.Migration
import by.bsuir.vadzim.weather20.database.DbConstants

object WeatherMigrations {
    private val MIGRATION_1_2 = Migration(1, 2) { db ->
        db.execSQL(
            """
           ALTER TABLE WeatherInfo ADD COLUMN description TEXT
        """.trimIndent()
        )
    }
    private val MIGRATION_2_3 = Migration(2, 3) { db ->
        db.execSQL(
            """
            UPDATE WeatherInfo SET description = " " WHERE description is NULL
            CREATE TABLE IF NOT EXISTS `WeatherInfo_temp` (
            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `type` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL,
            'description` TEXT NOT NULL
             
            INSERT INTO WeatherInfo_temp (id, type, isFavorite, description)
            SELECT id, type, isFavorite, description FROM WeatherInfo;
            
            DROP TABLE WeatherInfo
            
            ALTER TABLE WeatherInfo_temp RENAME TO WeatherInfo
             )
        """.trimIndent()
        )
    }
    private val MIGRATION_3_4 = Migration(3, 4) { db ->
        db.execSQL(
            """
            ALTER TABLE ${DbConstants.OLD_TABLE_NAME} RENAME TO ${DbConstants.TABLE_NAME}
        """.trimIndent()
        )
    }

    val ALL_MIGRATIONS by lazy {
        arrayOf(
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4
        )
    }
}

