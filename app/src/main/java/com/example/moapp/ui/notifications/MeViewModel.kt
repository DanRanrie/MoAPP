package com.example.moapp.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.moapp.logic.Repository
import com.example.moapp.logic.dao.UserDao

class MeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是余书"
    }
    val text: LiveData<String> = _text
    private val _user = MutableLiveData<String>().apply {
        value = "请登录！"
    }
    var user : LiveData<String> = _user
    fun saveUser(user:String) = Repository.saveUser(user)

    fun getSavedUser() = Repository.getSavedUser()

    fun isUserSaved() = Repository.isUserSaved()

    fun clearUserSaved() = Repository.clearUserSaved()
}