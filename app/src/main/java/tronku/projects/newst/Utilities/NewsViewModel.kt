package tronku.projects.newst.Utilities

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tronku.projects.newst.Networking.APIError
import tronku.projects.newst.Networking.ApiResponse
import tronku.projects.newst.Networking.Article
import kotlin.coroutines.CoroutineContext

class NewsViewModel(): ViewModel() {

    private val TAG = "NewsViewModel"
    private val coroutineContext: CoroutineContext = Job() + Dispatchers.Default

    private val repository = NewsRepository()
    private val mutableNewsLiveData = MutableLiveData<ArrayList<Article>>()
    private val mutableErrorLiveData = MutableLiveData<APIError>()
    private val mutableIsLoadingLiveData = MutableLiveData<Boolean>()
    val newsLiveData: LiveData<ArrayList<Article>>
    val errorLiveData: LiveData<APIError>
    val isLoadingLiveData: LiveData<Boolean>

    init {
        newsLiveData = mutableNewsLiveData
        errorLiveData = mutableErrorLiveData
        isLoadingLiveData = mutableIsLoadingLiveData
    }

    fun getNews (country: String = "in") {

        CoroutineScope(coroutineContext).launch {

            mutableIsLoadingLiveData.postValue(true)
            when (val news = repository.getNews(country)) {
                is ApiResponse.Success -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableNewsLiveData.postValue(news.output?.articles)
                    Log.e(TAG, "SUCCESS - getNews()")
                }

                is ApiResponse.Failure -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableErrorLiveData.postValue(news.apiError)
                    Log.e(TAG, "FAILURE - getNews()")
                }
            }
        }
    }

    fun getCategNews (category: String) {

        CoroutineScope(coroutineContext).launch {

            mutableIsLoadingLiveData.postValue(true)
            when (val news = repository.getNewsFromCategory(category)) {
                is ApiResponse.Success -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableNewsLiveData.postValue(news.output?.articles)
                    Log.e(TAG, "SUCCESS - getCategNews()")
                }

                is ApiResponse.Failure -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableErrorLiveData.postValue(news.apiError)
                    Log.e(TAG, "FAILURE - getCategNews()")
                }
            }
        }
    }

    fun getSearchedNews (query: String) {

        CoroutineScope(coroutineContext).launch {

            mutableIsLoadingLiveData.postValue(true)
            when (val news = repository.getSearchedNews(query)) {
                is ApiResponse.Success -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableNewsLiveData.postValue(news.output?.articles)
                    Log.e(TAG, "SUCCESS - getSearchedNews()")
                }

                is ApiResponse.Failure -> {
                    mutableIsLoadingLiveData.postValue(false)
                    mutableErrorLiveData.postValue(news.apiError)
                    Log.e(TAG, "FAILURE - getSearchedNews()")
                }
            }
        }
    }

}