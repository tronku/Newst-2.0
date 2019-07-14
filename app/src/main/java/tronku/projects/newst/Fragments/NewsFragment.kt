package tronku.projects.newst.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tronku.projects.newst.Networking.RetrofitService

import tronku.projects.newst.R

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        fetchNews()
        return view
    }

    private fun fetchNews() {
        val newsList = RetrofitService.getHeadlines()
        Log.d(tag, newsList.value?.get(0)!!.headline)
    }

}
