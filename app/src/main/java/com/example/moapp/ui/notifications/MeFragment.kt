package com.example.moapp.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moapp.ui.login.LoginActivity
import com.example.moapp.R
import com.example.moapp.databinding.FragmentMeBinding
import com.example.moapp.logic.dao.UserDao
import com.google.android.material.card.MaterialCardView

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        meViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val userInfoCard = root.findViewById<MaterialCardView>(R.id.userInfoCard)
        userInfoCard.setBackgroundColor(0x00FF0000.toInt())

        val userName = root.findViewById<TextView>(R.id.userName)
        if(meViewModel.isUserSaved()){
            userName.text = meViewModel.getSavedUser()
        }
        // 给用户名标签绑定事件
        userName.setOnClickListener {
            if (userName.text == "请登录！" && !meViewModel.isUserSaved()){
                // 如果用户名等于空，那么就跳转到登录Activity
                // 但其实不能这么干，应该打tag，因为用户可能会取名叫请登录
                // 测试模式，先这样搞
                val intent = Intent(root.context , LoginActivity::class.java)
                startActivity(intent)
            }
        }
        val debug = root.findViewById<LinearLayout>(R.id.debugUse)
        debug.setOnClickListener{
            meViewModel.clearUserSaved()
        }
//        meViewModel.user.observe(viewLifecycleOwner , Observer {
//            user ->
//
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}