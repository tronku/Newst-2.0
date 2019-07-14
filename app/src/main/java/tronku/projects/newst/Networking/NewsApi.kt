package tronku.projects.newst.Networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getHeadlinesAsync(@Query("country") country: String): Response<NewsModel>

    @GET("top-headlines")
    suspend fun getNewsFromCategoryAsync(@Query("category") category: String): Response<NewsModel>

    @GET("everything")
    suspend fun getSearchedNewsAsync(@Query("query") query: String): Response<NewsModel>

}