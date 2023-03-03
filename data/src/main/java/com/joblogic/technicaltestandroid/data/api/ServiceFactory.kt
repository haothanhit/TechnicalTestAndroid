package com.joblogic.technicaltestandroid.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {
    private const val OK_HTTP_TIMEOUT = 60L


    fun createMainService(isDebug: Boolean, baseUrl: String): MainService {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(MainService::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(createLoggingInterceptor(isDebug)))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(createDummy())
            .addNetworkInterceptor(createDummyNetwork())
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }


    private fun createDummy(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .build()
            val response = chain.proceed(request)
            println("Dummy interceptor fired!\n\nRequest: ${request.headers}\nResponse: ${response.headers}")
            return@Interceptor response
        }
    }

    private fun createDummyNetwork(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            println("Dummy network interceptor fired!\n\nRequest: ${request.headers}\nResponse: ${response.body}")
            return@Interceptor response
        }
    }


}
