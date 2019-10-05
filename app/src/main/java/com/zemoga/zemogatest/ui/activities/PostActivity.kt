package com.zemoga.zemogatest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.fragments.PostListFragment
import kotlinx.android.synthetic.main.activity_post_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PostDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (savedInstanceState == null) {
            // Add fragment to Activity
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer,
                    PostListFragment.newInstance()
                )
                .addToBackStack("PostsListFragment")
                .commit()
        }
    }
}
