package com.example.moapp.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moapp.MainActivity
import com.example.moapp.MoApplication
import com.example.moapp.R
import com.example.moapp.databinding.FragmentInfoBinding
import com.example.moapp.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val myTag = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        infoViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        // tab布局控件
        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        // 默认选中当前tab
        tabLayout.getTabAt(myTag)?.select()
        // 并且选中后，加大字体或者加粗
        // 暂时没找到好的方法实现

        // 设置长按不显示
        for(i in 0 until 3){
            hideToolTipText(tabLayout.getTabAt(i))
//            Log.d("fsdfsd", tabLayout.getTabAt(i)?.text.toString())
        }


        // 下拉控件
        val recyclerView = root.findViewById<RecyclerView>(R.id.infoRelativelayout)
        val layoutManager = GridLayoutManager(root.context,2)
        recyclerView.layoutManager = layoutManager
        infoViewModel.initData()
        val adapter = InfoAdapter(root.context, infoViewModel.infoArrayList)
        recyclerView.adapter = adapter
        // 第一个事件
        tabLayout.getTabAt(0)?.view?.setOnClickListener{
            infoViewModel.initData()
            adapter.notifyDataSetChanged()
        }
        // 第二个事件
        tabLayout.getTabAt(1)?.view?.setOnClickListener{
            infoViewModel.initAuthor()
            adapter.notifyDataSetChanged()
        }
        // 第三个事件
        tabLayout.getTabAt(2)?.view?.setOnClickListener{
            infoViewModel.initHitory()
            adapter.notifyDataSetChanged()
        }
        return root
    }
    private fun hideToolTipText(tab: TabLayout.Tab?) {
        // 取消长按事件  没什么用 不知道为什么
        if (tab != null) {
            tab.view.isLongClickable = false
        }
        // 可能是版本太高 高于api26 采用空提示
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            if (tab != null) {
                tab.view.tooltipText = ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}