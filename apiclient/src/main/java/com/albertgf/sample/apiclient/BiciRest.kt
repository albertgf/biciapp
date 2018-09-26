package com.albertgf.sample.apiclient

import com.albertgf.sample.apiclient.model.StationsApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

internal interface BiciRest {


    @GET("networks/bicing")
    fun getStations(): Call<StationsApi>

    companion object {
        fun create(debug: Boolean, url: String): BiciRest {
            val okHttpClient = OkHttpClient.Builder();

            if(debug) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                interceptor.setLevel((HttpLoggingInterceptor.Level.BODY))
                okHttpClient.addNetworkInterceptor(interceptor);
            }

            val gson = GsonBuilder().setLenient().create();

            okHttpClient.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.MINUTES)
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(url)
                    .client(okHttpClient.build())
                    .build()

            return retrofit.create(BiciRest::class.java)
        }
    }
}