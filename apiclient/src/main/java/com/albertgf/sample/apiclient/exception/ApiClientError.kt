package com.albertgf.sample.apiclient.exception

sealed class ApiClientError

data class UnknownApiError(val code:Int) : ApiClientError()
object NetworkError: ApiClientError()
object ItemNotFoundError : ApiClientError()