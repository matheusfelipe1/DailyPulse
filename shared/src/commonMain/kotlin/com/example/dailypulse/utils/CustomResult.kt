package com.example.dailypulse.utils

class CustomResult<T, E>(
    data: T? = null,
    error: E? = null
) {

    private var data: T? = null
    private var error: E? = null

    init {
        this.data = data
        this.error = error
    }

    companion object {
        fun <T, E> success(data: T): CustomResult<T, E> {
            return CustomResult(data = data)
        }

        fun <T, E> failure(error: E): CustomResult<T, E> {
            return CustomResult(error = error)
        }
    }

    suspend fun processResult(
        onSuccess: suspend (data: T) -> Unit,
        onFailure: suspend (error: E) -> Unit,
    ) {
        if (data != null) {
            onSuccess(data!!)
        } else if (error != null) {
            onFailure(error!!)
        }
    }
}
