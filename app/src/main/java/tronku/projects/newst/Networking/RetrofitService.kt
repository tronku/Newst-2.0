package tronku.projects.newst.Networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val API_KEY = "a51eb724637d4bd9b0569d1b92562b7c"

    private var retrofit: Retrofit? = null

    init {
        if (retrofit == null)
            retrofit = getClient()
    }

    fun getService(): NewsApi {
        return retrofit!!.create(NewsApi::class.java)
    }

    private fun getClient(): Retrofit {

        val interceptor = Interceptor {chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("apiKey", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}