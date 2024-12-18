package com.example.moapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.moapp.logic.Repository
import com.example.moapp.logic.Result
import com.example.moapp.logic.dao.UserDao
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // 用于存储用户名和密码的LiveData（虽然在这个例子中可能不是必需的）
    private val _loginCredentials = MutableLiveData<Pair<String, String>>()
    val loginCredentials: MutableLiveData<Pair<String, String>> get() = _loginCredentials

    // 用于存储登录结果的LiveData
    val loginInfo = MutableLiveData<Result<String>>()

    // 调用此函数以触发登录过程
    fun loginAccess(userphone : String , password : String) {
        viewModelScope.launch {
            val result = Repository.loginAccess(userphone, password)
            loginInfo.value = result
            // 根据result的值，您可能希望在这里执行一些额外的操作，比如导航到另一个屏幕或显示错误消息
        }
    }
    fun saveUser(user:String) = Repository.saveUser(user)

    fun getSavedUser() = Repository.getSavedUser()

    fun isUserSaved() = Repository.isUserSaved()

    fun clearUserSaved() = Repository.clearUserSaved()
}