package com.example.moapp.ui.forum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.moapp.R
import com.example.moapp.ui.forum.ItemFragment.ForumItemFragment

class ForumActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forumlayout)
        // 获取FragmentManager和开始一个FragmentTransaction
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // 添加或替换Fragment
        val forumItemFragment = ForumItemFragment()
        fragmentTransaction.add(R.id.fragment_container, forumItemFragment)
        // 如果你想要替换已经存在的Fragment，可以使用replace方法
        // fragmentTransaction.replace(R.id.fragment_container, myFragment)

        // 提交事务
        fragmentTransaction.commit()
    }
}
