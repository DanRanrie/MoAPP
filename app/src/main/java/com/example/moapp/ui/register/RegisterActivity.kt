package com.example.moapp.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moapp.R
import com.example.moapp.UserAgreementActivity
import com.example.moapp.logic.Result
import com.example.moapp.ui.login.LoginViewModel


class RegisterActivity : ComponentActivity() {
    val registerViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerlayout)
        val userPhone = findViewById<EditText>(R.id.edit_userphone)
        val userName = findViewById<EditText>(R.id.edit_username)
        val userPswd = findViewById<EditText>(R.id.edit_password)
        val userPswdRepeat = findViewById<EditText>(R.id.edit_password_repeat)
        val registerBtn = findViewById<Button>(R.id.registerbtn)
        val userAgreement = findViewById<CheckBox>(R.id.checkBox)
        val userAgreementText = findViewById<TextView>(R.id.userAgreement)
        userAgreementText.setOnClickListener {
            val intent = Intent(this,UserAgreementActivity::class.java)
            startActivity(intent)
        }
        registerBtn.setOnClickListener {
            // 判定
            // 1. 四个输入框不为空
            // 2. 手机号符合规则  // 基础一下，仅仅长度
            // 3. 密码一致
            // 4. 勾选用户条约
            // 5. （需要用到网络功能） 用户手机号是否被注册
            if(!userPhone.text.isEmpty() && !userName.text.isEmpty() && !userPswd.text.isEmpty() && !userPswdRepeat.text.isEmpty()) {
                if(userPhone.text.length == 11){
                    if(userPswd.text.toString() == userPswdRepeat.text.toString()){
                        if(userAgreement.isChecked){
                            registerViewModel.registerAccess(userPhone.text.toString() , userName.text.toString() , userPswd.text.toString())
//                            this.finish()
                        }
                        else{
                            // 请阅读并同意用户协议
                            Toast.makeText(this,"请阅读并同意用户协议！",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        // 密码不一致
                        Toast.makeText(this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    // 手机号长度不足11位
                    Toast.makeText(this,"手机号格式不正确！",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                // 输入框为空
                Toast.makeText(this,"您输入的存在空！",Toast.LENGTH_SHORT).show()
            }
        }
        registerViewModel.registerInfo.observe(this, Observer { result ->
            result?.let {
                if (it is Result.Success) {
                    // 信息回显成功，处理成功逻辑
                    if(it.data == "PhoneNumber Repeated"){
                        Toast.makeText(this, "手机号已被注册！", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "注册成功，请进行登录！", Toast.LENGTH_SHORT).show()
                        this.finish()
                    }
                    // 可以在这里添加导航到另一个Activity或Fragment的代码
                } else if (it is Result.Failure) {
                    // 登录失败，处理失败逻辑
                    Toast.makeText(this, "注册失败: ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}


