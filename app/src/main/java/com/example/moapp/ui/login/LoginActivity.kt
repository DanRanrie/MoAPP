package com.example.moapp.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moapp.R
import com.example.moapp.ui.register.RegisterActivity

class LoginActivity : ComponentActivity() {
    val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)
        val editUsername = findViewById<EditText>(R.id.edit_username)
        val editPassword = findViewById<EditText>(R.id.edit_password)
        val loginBtn = findViewById<Button>(R.id.btn_login)
        val meInfo = findViewById<TextView>(R.id.userName)
        val registerTextView = findViewById<TextView>(R.id.textview_register)
        registerTextView.setOnClickListener {
            // register
            val intent = Intent(this , RegisterActivity::class.java)
            startActivity(intent)
        }
        loginBtn.setOnClickListener {
            if(editUsername.text.isEmpty()) {
                Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show()
            }
            else if(editPassword.text.isEmpty()) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            }
            else{
                loginViewModel.loginAccess(editUsername.text.toString() , editPassword.text.toString())
            }
        }
        loginViewModel.loginInfo.observe(this, Observer { result ->
            result?.let {
                if (it is com.example.moapp.logic.Result.Success) {
                    // 信息回显成功，处理成功逻辑
                    if(it.data == "Password Error!"){
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                    }
                    else if (it.data == "User Not Exist!"){
                        Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        loginViewModel.saveUser(it.data)  // 缓存数据
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                        this.finish()
                    }
                    // 可以在这里添加导航到另一个Activity或Fragment的代码
                } else if (it is com.example.moapp.logic.Result.Failure) {
                    // 登录失败，处理失败逻辑
                    Toast.makeText(this, "登录失败: ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
