package com.zemoga.zemogatest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import kotlinx.android.synthetic.main.activity_post.*
import timber.log.Timber

/**
 * An activity representing a list of Post.
 *
 */
class PostActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(toolbar)
        toolbar.title = title

        delete.setOnClickListener(onClick())

        setupNavigation()
    }

    private fun onClick() = View.OnClickListener { view ->
        Snackbar.make(view, "Delete?", Snackbar.LENGTH_LONG)
            .setAction("OK", oKListener()).show()
    }

    private fun oKListener() = View.OnClickListener { view ->
        Timber.d("Deleting Post")
        val postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postViewModel.setPostList(emptyList())

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()

    private fun setupNavigation() {
        navigationController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navigationController)
    }
}
