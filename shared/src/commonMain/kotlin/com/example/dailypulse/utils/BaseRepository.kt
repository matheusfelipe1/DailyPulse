package com.example.dailypulse.utils

open class BaseRepository {
    open fun <T, E> handlerSuccess(data: T): CustomResult<T, E> {
        return CustomResult.success(data = data)
    }

    open fun <T, E> handlerFailure(error: E): CustomResult<T, E> {
        return CustomResult.failure(error = error)
    }
}