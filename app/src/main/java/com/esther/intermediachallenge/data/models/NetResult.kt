package com.esther.intermediachallenge.data.models

import retrofit2.Response
import java.io.Serializable


abstract class NetResult {
    protected suspend fun <T> getResult(call: suspend () -> Response<ApiResponse<Data<T>>>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()?.data?.results
//                if (body != null) return Resource.success(body)
                return Resource.success(body)
            }
            return Resource.error("${response.code()}: ${response.message()}")
        } catch (e: Exception) {
            return Resource.error(e.message ?: "Generic error")
        }
    }
}
//clase que permite encapsular las respuesta del repository segun su estado loading, success y error
data class Resource<out T>(var status: Status, val data: T?, val message: String?) : Serializable {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}