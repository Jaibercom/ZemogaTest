package com.zemoga.zemogatest

import android.app.Application
import com.zemoga.zemogatest.db.AppDatabase
import com.zemoga.zemogatest.db.entity.PostEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


class ZemogaApplication : Application() {

    companion object {
        var database: AppDatabase? = null
            private set
    }


    override fun onCreate() {
        super.onCreate()
        // Timber init
        Timber.plant(Timber.DebugTree())

        database = AppDatabase.getDatabase(this)

//        GlobalScope.launch {
//            database!!.postDao().insertAll(
//                PostEntity(1, "Content 1", "body 1"),
//                PostEntity(2, "Content 2", "body 2"),
//                PostEntity(3, "Content 3", "body 3"),
//                PostEntity(4, "Content 4", "body 4")
//            )
//            val data = database!!.postDao().getAll2()
//
//            data.forEach {
////                println(it)
//                Timber.d(it.title)
//            }
//        }


    }
}
