package tronku.projects.newst.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = fragList[position]

    override fun getCount(): Int = fragList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]

    fun addFragments(fragment: Fragment, title: String) {
        fragList.add(fragment)
        titleList.add(title)
    }
}