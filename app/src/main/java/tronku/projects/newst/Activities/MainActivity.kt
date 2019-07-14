package tronku.projects.newst.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import tronku.projects.newst.Adapters.MainViewPager
import tronku.projects.newst.Fragments.DiscoverFragment
import tronku.projects.newst.Fragments.NewsFragment
import tronku.projects.newst.R

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        adapter = MainViewPager(supportFragmentManager)
        setupViewPager()

        tab_layout.setupWithViewPager(viewpager)
    }

    fun setupViewPager() {
        adapter.addFragments(NewsFragment(), "News")
        adapter.addFragments(DiscoverFragment(), "Discover")
        viewpager.adapter = adapter
    }
}
