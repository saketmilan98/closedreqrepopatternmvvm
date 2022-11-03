package app.asgn.mvvmpractice.network

import app.asgn.mvvmpractice.BuildConfig
import app.asgn.mvvmpractice.app.MyApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance() : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { it.proceed(it.request().newBuilder().addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_AUTH_TOKEN}").build()) }
            .build()

        return Retrofit.Builder()
            .baseUrl(MyApp.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}