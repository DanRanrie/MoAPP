package com.example.moapp.logic

import com.example.moapp.logic.network.LoginService
import com.example.moapp.logic.network.netWork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object netOperator {

    private val LoginService = netWork.create<LoginService>()

    // await 等待结果 阻塞
    suspend fun loginAccess(userphone : String , password : String) = LoginService.loginAccess(userphone, password).await()
    suspend fun registerAccess(userphone : String ,username : String , password : String) = LoginService.registerAccess(userphone, username, password).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null)
                        continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}