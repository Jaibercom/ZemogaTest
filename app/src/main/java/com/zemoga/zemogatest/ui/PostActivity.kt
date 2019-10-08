package com.zemoga.zemogatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.zemoga.zemogatest.R
import kotlinx.android.synthetic.main.activity_post.toolbar

/**
 * An activity representing a list of Post.
 */
class PostActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(toolbar)
        toolbar.title = title

        setupNavigation()
    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()

    private fun setupNavigation() {
        navigationController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navigationController)
    }
}
