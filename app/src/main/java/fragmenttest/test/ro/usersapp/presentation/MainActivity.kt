package fragmenttest.test.ro.usersapp.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fragmenttest.test.ro.usersapp.R
import fragmenttest.test.ro.usersapp.presentation.usersList.UsersFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        supportFragmentManager.beginTransaction().apply {
            val fragment = UsersFragment.newInstance()
            add(R.id.fragmentLayout, fragment, UsersFragment.TAG)
            commitNowAllowingStateLoss()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
}