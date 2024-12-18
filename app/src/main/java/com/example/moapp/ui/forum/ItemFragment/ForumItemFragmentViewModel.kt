package com.example.moapp.ui.forum.ItemFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.moapp.R
import com.example.moapp.logic.model.ForumItemInfo
import de.hdodenhof.circleimageview.CircleImageView
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue


class ForumItemFragmentViewModel : ViewModel() {
    val forumItemList = ArrayList<ForumItemInfo>()
    class ForumItemInfoAdapt(val forumItemInfoList : List<ForumItemInfo>) : RecyclerView.Adapter<ForumItemInfoAdapt.ViewHolder>(){
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val userHeadshot : CircleImageView = view.findViewById(R.id.forumUserHeadshot)
            val userName: TextView = view.findViewById(R.id.forumUserName)
            val title : TextView = view.findViewById(R.id.forumItemTitle)
            val content : TextView = view.findViewById(R.id.forumItemContent)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.forum_info_item_layout, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = forumItemInfoList.size
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val forumItem = forumItemInfoList[position]
            holder.userName.text = forumItem.userName
            holder.userHeadshot.setImageResource(forumItem.userHeadshot)
            holder.title.text = forumItem.title
            holder.content.text = forumItem.content
        }
    }
    fun initData() {
        repeat(200) {
            forumItemList.add(ForumItemInfo("杀软二刺猿",(R.drawable.headshot),"小死神真是太可爱了","18块买的划算"))
            forumItemList.add(ForumItemInfo("雪菜",(R.drawable.xc),"我是卷狗","卷死你们"))
            forumItemList.add(ForumItemInfo("杀软二刺猿",(R.drawable.headshot),"强烈推荐大家买犹格索托斯庭院","小死神真的很可爱，大家可以来捏一下哦QwQ"))
            forumItemList.add(ForumItemInfo("默认用户名",(R.drawable.headshot2),"刚来","萌新刚来，不太懂，这个软件是干什么的"))
            forumItemList.add(ForumItemInfo("不吃香菜（考研版）",(R.drawable.headshot3),"小红书经典语录（10条）","1.永远不要和不爱你的人..."))
            forumItemList.add(ForumItemInfo("danranrie",(R.drawable.headshot4),"OK熬兄弟们我宣布个事情","我就是软件制作者"))

        }
    }
    class DividerItemDecoration(
        context: Context,
        dividerHeight: Int = 1, // 间隔高度，默认1dp
        dividerColor: Int = 0x7AE7E7E7 // 间隔颜色，默认灰色
    ) : RecyclerView.ItemDecoration() {

        private val dividerPaint: Paint = Paint().apply {
            color = dividerColor
            strokeWidth = dividerHeight.toFloat()
        }

        // 将dp转换为px
        private val actualDividerHeight: Int = with(context.resources) {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dividerHeight.toFloat(),
                displayMetrics
            ).toInt()
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0 until childCount - 1) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + actualDividerHeight

                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
            }
        }
    }
}