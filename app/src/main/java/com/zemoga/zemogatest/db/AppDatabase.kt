package com.zemoga.zemogatest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zemoga.zemogatest.db.dao.PostDao
import com.zemoga.zemogatest.db.entity.PostEntity

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "post_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
