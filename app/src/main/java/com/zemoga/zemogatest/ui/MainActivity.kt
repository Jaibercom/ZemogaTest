package com.zemoga.zemogatest.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.db.AppDatabase
import com.zemoga.zemogatest.db.entity.PostEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
//        val db = AppDatabase.getDatabase(this)
//
//        GlobalScope.launch {
//            db.postDao().insertAll(
//                PostEntity(1, "Content 1", "body 1"),
//                PostEntity(2, "Content 2", "body 2"),
//                PostEntity(3, "Content 3", "body 3"),
//                PostEntity(4, "Content 4", "body 4")
//            )
//            val data = db.postDao().getAll2()
//
//            data.forEach {
////                println(it)
//                Timber.d(it.title)
//            }
//        }


    }

    private fun setupNavigation() {
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_post_list,
                R.id.navigation_favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun showNavBottom(isVisible: Boolean) {
        if (isVisible) {
            navView.visibility = View.VISIBLE
        } else {
            navView.visibility = View.GONE
        }
    }
}
