package com.example.moapp.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.moapp.MoApplication

object UserDao {
    fun saveUser(userInfo : String) {
        sharedPreferences().edit {
            putString("userInfo" , userInfo)
        }
    }
    fun getSavedUser() : String? {
        val userInfo = sharedPreferences().getString("userInfo", "")
        return userInfo
    }
    fun isUserSaved() = sharedPreferences().contains("userInfo")
    // 清空缓存 调试用
    fun clearUserSaved(){
        sharedPreferences().edit {
            remove("userInfo")
        }
    }
    private fun sharedPreferences() = MoApplication.context.getSharedPreferences("userCache", Context.MODE_PRIVATE)
}