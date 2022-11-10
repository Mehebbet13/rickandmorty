package com.example.abbtechtestapplication.api

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@InstallIn(SingletonComponent::class)
@Module
class RetrofitInstance {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        initLogger(getOkHTTPClient())
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun getCharacterAPI(retrofit: Retrofit): RickAndMortyApi {
        return retrofit.create(RickAndMortyApi::class.java)
    }

    private fun initLogger(httpClient: OkHttpClient.Builder) {
        try {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
      } catch (e: java.lang.Exception) {
            //not implemented yet
        }
    }
    private fun getOkHTTPClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().also {
            val certPinner = CertificatePinner.Builder().build()
            it.certificatePinner(certPinner)
        }
    }
}