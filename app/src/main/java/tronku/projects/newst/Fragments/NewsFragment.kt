package tronku.projects.newst.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_news.*
import tronku.projects.newst.Networking.NewsModel
import tronku.projects.newst.Networking.RetrofitService

import tronku.projects.newst.R
import tronku.projects.newst.Utilities.NewsViewModel
import tronku.projects.newst.Utilities.NewsViewModelFactory

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var viewModelFactory: NewsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_news, container, false)

        viewModelFactory = NewsViewModelFactory(context!!)
        newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        fetchNews()
    }

    private fun setObservers() {
        newsViewModel.newsLiveData.observe(this, Observer {
            api_result.text = Gson().toJson(it)
            error_layout.visibility = View.GONE
        })

        newsViewModel.errorLiveData.observe(this, Observer {
            Log.e("ERROR", Gson().toJson(it))
            loading_layout.visibility = View.GONE
            error_layout.visibility = View.VISIBLE
            error_text.text = it.msg

            retry_button.setOnClickListener {
                error_layout.visibility = View.GONE
                fetchNews()
            }
        })

        newsViewModel.isLoadingLiveData.observe(this, Observer {
            loading_layout.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun fetchNews() {
        loading_layout.visibility = View.VISIBLE
        newsViewModel.getNews()
    }

}
