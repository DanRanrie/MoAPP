package com.example.moapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moapp.logic.Repository
import com.example.moapp.logic.Result
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    // 用于存储结果的LiveData
    val registerInfo = MutableLiveData<Result<String>>()

    // 调用此函数以触发登录过程
    fun registerAccess(userphone : String ,username : String , password : String) {
        viewModelScope.launch {
            val result = Repository.registerAccess(userphone,username, password)
            registerInfo.value = result
        }
    }
}