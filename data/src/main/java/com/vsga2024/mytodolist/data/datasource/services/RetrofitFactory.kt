package com.vsga2024.mytodolist.data.datasource.services

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRetrofitBuilder(
    context: Context,
    baseUrl: String,
    authLocalDataSource: AuthLocalDataSource? = null,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkhttpClient(context, authLocalDataSource))
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(
    context: Context,
    authLocalDataSource: AuthLocalDataSource? = null,
): OkHttpClient {
    val okHttpClient = if (authLocalDataSource != null) {
        OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(provideChuckerInterceptor(context))
            .addInterceptor(provideHttpHeaderInterceptor(context, authLocalDataSource))
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(provideChuckerInterceptor(context))
            .build()
    }

    return okHttpClient
}

private fun provideHttpHeaderInterceptor(
    context: Context,
    authLocalDataSource: AuthLocalDataSource,
): Interceptor {
    return Interceptor { chain ->
        val token = runBlocking { authLocalDataSource.getToken() }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(request)
    }
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

private fun provideChuckerInterceptor(context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context).build()
}