package com.example.codingtest.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class ErrorHandler(private var retrofit: Retrofit) {

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>, response: Response<*>?): T? {
        val errorBody = response?.errorBody()
        return if (errorBody != null) {
            val converter: Converter<ResponseBody, T> = retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
            converter.convert(errorBody)
        } else {
            null
        }
    }

    fun getErrorCode(response: Response<*>?): Int? {
        return response?.code()
    }
}