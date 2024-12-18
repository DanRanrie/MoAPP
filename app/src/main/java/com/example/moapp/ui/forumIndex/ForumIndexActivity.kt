package com.example.moapp.ui.forumIndex



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.viewpager.widget.ViewPager
import com.example.moapp.R
import com.example.moapp.logic.model.LoopViewAdapter
import com.example.moapp.ui.forum.ForumActivity


class ForumIndexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forumindexlayout)
        val FriendsTakingAboutWordsLayout = findViewById<LinearLayout>(R.id.FriendsTakingAboutWordsLayout)
        FriendsTakingAboutWordsLayout.setOnClickListener{
            val intent = Intent(this , ForumActivity::class.java)
            startActivity(intent)
        }
        // 调用初始化函数
        initLoopView()
    }
    private fun initLoopView() {
        val viewPager = findViewById(R.id.viewPager) as ViewPager
        val ll_dots_container = findViewById<LinearLayout>(R.id.ll_dots_loop)
        val loop_dec = findViewById<TextView>(R.id.loop_dec)

        // 图片资源id数组
        val mImg = intArrayOf(
            R.drawable.li_shu,
            R.drawable.kai_shu,
            R.drawable.xing_shu,
            R.drawable.cao_shu,
            R.drawable.zuan_shu
        )

        // 文本描述
        val mDec = arrayOf(
            "Test1",
            "Test2",
            "Test3",
            "Test4",
            "Test5"
        )

        val mImg_id = intArrayOf(
            R.id.pager_img1,
            R.id.pager_img2,
            R.id.pager_img3,
            R.id.pager_img4,
            R.id.pager_img5
        )

        // 初始化要展示的5个ImageView
        val mImgList = mutableListOf<ImageView>()
        for (i in mImg.indices) {
            // 初始化要显示的图片对象
            val imageView = ImageView(this).apply {
                setBackgroundResource(mImg[i])
                id = mImg_id[i]
                setOnClickListener(pagerOnClickListener(this@ForumIndexActivity))
            }
            mImgList.add(imageView)

            // 加引导点
            val dotView = View(this).apply {
                setBackgroundResource(R.drawable.dot_selector)
                layoutParams = LinearLayout.LayoutParams(10, 10).apply {
                    if (i != 0) {
                        leftMargin = 10
                    }
                }
                isEnabled = false // 设置默认所有都不可用
            }
            ll_dots_container.addView(dotView, dotView.layoutParams)
        }

        ll_dots_container.getChildAt(0).isEnabled = true
        loop_dec.text = mDec[0]
        var previousSelectedPosition = 0

        // 设置适配器
        viewPager.adapter = LoopViewAdapter(mImgList)

        // 把ViewPager设置为默认选中Integer.MAX_VALUE / 2，从十几亿次开始轮播图片，达到无限循环目的;
        val m = (Integer.MAX_VALUE / 2) % mImgList.size
        val currentPosition = (Integer.MAX_VALUE / 2 - m).toInt()
        viewPager.setCurrentItem(currentPosition)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val newPosition = position % mImgList.size
                loop_dec.text = mDec[newPosition]
                ll_dots_container.getChildAt(previousSelectedPosition).isEnabled = false
                ll_dots_container.getChildAt(newPosition).isEnabled = true
                previousSelectedPosition = newPosition
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        // 开启轮询
        Thread {
            val isRunning = true
            while (isRunning) {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    viewPager.setCurrentItem(viewPager.currentItem + 1)
                }
            }
        }.start()
    }
}
