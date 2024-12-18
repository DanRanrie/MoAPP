package com.example.moapp.ui.forumIndex

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moapp.R

class ForumIndexViewModel : ViewModel() {

}

class pagerOnClickListener(var mContext: Context) : View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.pager_img1 -> Toast.makeText(mContext, "图片1被点击", Toast.LENGTH_SHORT)
                .show()

            R.id.pager_img2 -> Toast.makeText(mContext, "图片2被点击", Toast.LENGTH_SHORT)
                .show()

            R.id.pager_img3 -> Toast.makeText(mContext, "图片3被点击", Toast.LENGTH_SHORT)
                .show()

            R.id.pager_img4 -> Toast.makeText(mContext, "图片4被点击", Toast.LENGTH_SHORT)
                .show()

            R.id.pager_img5 -> Toast.makeText(mContext, "图片5被点击", Toast.LENGTH_SHORT)
                .show()
        }
    }
}