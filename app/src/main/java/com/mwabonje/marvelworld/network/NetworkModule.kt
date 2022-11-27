package com.mwabonje.marvelworld.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mwabonje.marvelworld.BuildConfig
import com.mwabonje.marvelworld.models.DefaultResponse
import com.mwabonje.marvelworld.utils.Utilities
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * @author Lennox Brown.
 */
@Module
class NetworkModule {

    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    @Provides
    fun provideGson() = GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return loggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        utilities: Utilities
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                var request = chain.request()
                val originalUrl = request.url
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("apikey",BuildConfig.PUB_KEY)
                    .addQueryParameter("ts", utilities.time.toString())
                    .addQueryParameter("hash",utilities.md5Hash())
                    .build()

                request = request.newBuilder().url(newUrl).build()

                chain.proceed(request)
            })
            .retryOnConnectionFailure(true)

        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

}


interface MarvelApi {

    @GET("characters")
    suspend fun sendRequest(): Response<DefaultResponse>

    @GET("characters/{id}")
    suspend fun characterDetailsRequest(@Path(value = "id", encoded = true) id: String): Response<DefaultResponse>

}