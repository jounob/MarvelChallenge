package com.esther.intermediachallenge.data.repositories

import com.esther.intermediachallenge.BuildConfig
import com.esther.intermediachallenge.data.models.NetResult
import com.esther.intermediachallenge.data.models.Resource
import retrofit2.Response
import java.security.MessageDigest

abstract class BaseRepository {

    protected val authParams = AuthParams(BuildConfig.PUBLIC_API_KEY, 1, generateHash())
//
//    protected fun <T> handleResult(result: Response<T>): Resource<T> {
//        if (result.isSuccessful)
//            result.body()
//                ?.let { content -> return Resource.success(content) }
//        return Resource.error("ERROR 1")
//    }

    protected class AuthParams(
        private val apiKey: String,
        private val ts: Int,
        private val hash: String
    ) {
        fun getMap(): HashMap<String, String> {
            val hashMap = HashMap<String, String>()
            hashMap["apikey"] = apiKey
            hashMap["ts"] = ts.toString()
            hashMap["hash"] = hash
            return hashMap
        }
    }

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}").toByteArray())
        .joinToString("") { "%02x".format(it) }
}