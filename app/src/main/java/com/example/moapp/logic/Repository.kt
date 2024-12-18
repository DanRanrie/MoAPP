package com.example.moapp.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.moapp.logic.dao.UserDao
import kotlinx.coroutines.Dispatchers
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}
object Repository {

    fun saveUser(user:String) = UserDao.saveUser(user)

    fun getSavedUser() = UserDao.getSavedUser()

    fun isUserSaved() = UserDao.isUserSaved()

    fun clearUserSaved() = UserDao.clearUserSaved()


suspend fun loginAccess(userphone: String, password: String): Result<String> {
        return try {
            val response = netOperator.loginAccess(userphone, password) // 假设这是一个挂起函数
            if (response != "Logined Error!") {
                Result.Success(response)
            } else {
                Result.Failure(RuntimeException("Login failed with response: $response"))
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    suspend fun registerAccess(userphone: String, username: String, password: String): Result<String> {
        return try {
            val response = netOperator.registerAccess(userphone, username, password) // 假设这是一个挂起函数
            if (response != "Register Error") {
                Result.Success(response)
            } else {
                Result.Failure(RuntimeException("Register failed with response: $response"))
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}