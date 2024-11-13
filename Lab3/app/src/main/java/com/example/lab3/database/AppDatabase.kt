package com.example.lab3.database
import android.content.Context
import com.example.lab3.dao.FilmDao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab3.dao.CharacterDao
import com.example.lab3.models.Film
import com.example.lab3.models.Character

@Database(entities = [Film::class, Character::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}