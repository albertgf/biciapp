package com.albertgf.sample.apiclient

import com.albertgf.sample.apiclient.exception.ApiClientError
import com.albertgf.sample.apiclient.exception.ItemNotFoundError
import com.albertgf.sample.apiclient.exception.NetworkError
import com.albertgf.sample.apiclient.exception.UnknownApiError
import org.funktionale.either.Either
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class BiciApiClient {
    private val biciClient by lazy {
        BiciRest.create(BuildConfig.DEBUG, "http://api")
    }

    fun <T> execute(call: Call<T>): Either<ApiClientError, T?> = try {
        val response = call.execute()
        inspectResponseForErrors(response)
    } catch (e: IOException) {
        Either.left(NetworkError)
    }

    private fun <T> inspectResponseForErrors(response: Response<T>): Either<ApiClientError, T?> = when {
        response.code() == 404 -> Either.left(ItemNotFoundError)
        response.code() >= 400 -> Either.left(UnknownApiError(response.code()))
        else -> Either.right(response.body())
    }
}