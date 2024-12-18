package com.example.moapp.logic.model

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


class LoopViewAdapter(private val imageViewList: MutableList<ImageView>) :
    PagerAdapter() {
    // 1. 返回要显示的条目内容, 创建条目
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // container: 容器: ViewPager
        // position: 当前要显示条目的位置 0 -> 4
        // newPosition = position % 5
        val newPosition = position % imageViewList.size
        val img = imageViewList[newPosition]
        // a. 把View对象添加到container中
        container.addView(img)
        // b. 把View对象返回给框架, 适配器
        return img
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE //返回一个无限大的值，可以 无限循环!!!!!
    }

    /**
     * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
     */
    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }
}

