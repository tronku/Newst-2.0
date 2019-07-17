package tronku.projects.newst.Utilities

import android.content.Context
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import tronku.projects.newst.Database.NewsDatabase
import tronku.projects.newst.Networking.*


class NewsRepository(private val context: Context) {

    private val TAG = "NewsRepository"
    private var service: NewsApi = RetrofitService.getService()

    suspend fun getNews(country: String): ApiResponse<NewsModel> {
        return if (NewstApp.getInstance().isConnected()) {
            getHeadlines(country)
        } else {
            ApiResponse.Success(getNewsFromLocal())
        }
    }

    private suspend fun getHeadlines(country: String): ApiResponse<NewsModel> {
        return safeApiCall(
            call = { service.getHeadlinesAsync(country) },
            error = "Error in fetching news..."
        )
    }

    suspend fun getNewsFromCategory(category: String): ApiResponse<NewsModel> {
        return safeApiCall(
            call = { service.getNewsFromCategoryAsync(category) },
            error = "Error in fetching news..."
        )
    }

    suspend fun getSearchedNews(query: String): ApiResponse<NewsModel> {
        return safeApiCall(
            call = { service.getSearchedNewsAsync(query) },
            error = "Error in fetching news..."
        )
    }

    private suspend fun <T: Any> safeApiCall(call: suspend() -> Response<T>, error: String): ApiResponse<T> {
        return try {
            val result = call.invoke()
            if (result.isSuccessful)
                ApiResponse.Success(result.body()!!)
            else
                ApiResponse.Failure(APIError(result.code(), result.message()))
        } catch (e: HttpException) {
            ApiResponse.Failure(APIError(null, error))
        }
    }

    fun saveToLocal(newsModel: NewsModel?) {
        val db = NewsDatabase(context)

        GlobalScope.launch {
            db.NewsDao().deleteLocalNews()
            db.NewsDao().insertNewsToLocal(newsModel)
        }
    }

    private suspend fun getNewsFromLocal(): NewsModel? {
        val db = NewsDatabase(context)
        return withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
            db.NewsDao().getNewsFromLocal()
        }
    }

}