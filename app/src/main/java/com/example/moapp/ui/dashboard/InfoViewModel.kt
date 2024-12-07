package com.example.moapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moapp.InfoItemActivity
import com.example.moapp.R
import kotlin.math.log

class InfoViewModel : ViewModel() {

    // 书法信息
    val zuanShuInfo = "篆体，为汉字古代书体之一，也叫篆书，是对古文字的统称。“篆者，传也，传其物理，施之无穷。”古人认为篆书为仓颉所造，其实是不可信的。一种文字的产生是要经过一个较长的酝酿发展过程的，不可能由一个人在短时期能完成。\n" +
            "广义上的篆体包括隶书以前的所有书体以及延属，如金文、石鼓文、六国古文、小篆、缪篆、叠篆等。狭义上主要指“大篆”和“小篆”。篆书字划圆转，结构依六书之义，故唐孙过庭谓：“篆尚婉而通”。\n"
    val liShuInfo = "隶书，汉字的一种字体，有秦隶、汉隶等，一般认为由篆书发展而来，字形多呈宽扁，横画长而竖画短，讲究“蚕头燕尾”、“一波三折”。\n" +
            "根据出土简牍，隶书始创于秦朝（一说战国时期） [5-6]，传说程邈作隶，汉隶在东汉时期达到顶峰，上承篆书传统，下开魏晋、南北朝，对后世书法有不可小觑的影响，书法界有“汉隶唐楷”之称。"
    val kaiShuInfo = "楷书，汉字的一种字体，也叫楷体、正楷、真书、正书。由隶书逐渐演变而来，更趋简化，横平竖直。《辞海》书中解释说它“形体方正，笔画平直，可作楷模”。这种汉字字体端正，就是现代通行的汉字手写正体字。\n" +
            "楷书也是一种官名。《新唐书·百官志二》书中记中书省史馆有楷书二十人，写国史楷书十八人。楷书作为官名也称作楷书手，掌缮写之事，在同一机构中并置，因具体分工有所不同。《通典·职官二十二》记楷书手为流外勋品官。宋代无楷书手之称，惟称楷书。"
    val xingShuInfo = "行书，是一种书法统称，分为行楷和行草两种。它在楷书的基础上发展起源的，是介于楷书、草书之间的一种字体，是为了弥补楷书的书写速度太慢和草书的难以辨认而产生的。“行”是“行走”的意思，因此它不像草书那样潦草，也不像楷书那样端正。实质上它是楷书的草化或草书的楷化。楷法多于草法的叫“行楷”，草法多于楷法的叫“行草”。行书实用性和艺术性皆高，而楷书是文字符号，实用性高且见功夫；相比较而言，草书则是艺术性高，但是实用性显得相对不足"
    val caoShuInfo = "草书是汉字的一种字体，有广狭二义。广义的，不论年代，凡写的潦草的字都算作为草书。狭义的，即作为一种特定的字体， [1]形成于汉代 [2]，是为了书写简便在隶书基础上演变出来的。\n" +
            "大约从东晋时代开始，为了跟当时的新体草书相区别。把汉代的草书称作章草。新体草书相对而言称作今草，其又分大草（也称狂草）和小草，在狂乱中觉得优美。正如李志敏所说：“临于池，酌于理，师于物，得于心，悟于象，然后始入草书妙境。 ” [3]\n" +
            "草书由于字形太简单，彼此容易混淆，所以不能像隶书取代篆文那样，取代隶书而成为主要的字体。"

    // 作者信息
    val wxzInfo = "王羲之：被誉为“书圣”，他的书法兼善隶、草、楷、行各体，精研体势，心摹手追，广采众长，冶于一炉，摆脱了汉魏笔风，自成一家"
    val yzqInfo = "颜真卿：唐代中期杰出书法家，与柳公权并称“颜柳”，有“颜筋柳骨”之誉。"
    val wxzerInfo = "王献之：王羲之之子，自小跟随父亲练习书法，才华气度超过时人。"
    val lgqInfo = "柳公权：唐代书法家，与颜真卿并称“颜柳”。"
    val hsInfo = "怀素：唐代书法家，以草书闻名，与张旭齐名，人称“张颠素狂”或“颠张醉素”"
    val oyxInfo = "欧阳询：唐代书法家，与虞世南俱以书法驰名初唐，并称“欧虞”，后人以其书于平正中见险绝，最便于初学者，号为“欧体”。"
    val mfInfo = "米芾：北宋书法家、画家、书画理论家，与蔡襄、苏轼、黄庭坚合称“宋四家”"
    val htjInfo = "黄庭坚：北宋著名文学家、书法家，为盛极一时的江西诗派开山之祖，与杜甫、陈师道和陈与义素有“一祖三宗”（黄庭坚为其中一祖）之称。"
    val zyInfo = "智永：南朝、隋朝书法家，王羲之七世孙。居永欣寺阁上临书三十年。所退笔头皆埋之，号曰“退笔冢”"
    val zmbrsInfo = "赵孟頫：元代书法家、画家、诗人，宋太祖赵匡胤十一世孙、秦王赵德芳嫡派子孙。\n"

    val infoList  = mutableListOf(
        CalligraphyInfo("篆书",R.drawable.zuan_shu,zuanShuInfo),
        CalligraphyInfo("隶书",R.drawable.li_shu,liShuInfo),
        CalligraphyInfo("楷书",R.drawable.kai_shu,kaiShuInfo),
        CalligraphyInfo("行书",R.drawable.xing_shu,xingShuInfo),
        CalligraphyInfo("草书",R.drawable.cao_shu,caoShuInfo),
    )

    val authorList  = mutableListOf(
        CalligraphyInfo("王羲之",R.drawable.wxz,wxzInfo),
        CalligraphyInfo("颜真卿",R.drawable.yzq,yzqInfo),
        CalligraphyInfo("王献之",R.drawable.wxzer,wxzerInfo),
        CalligraphyInfo("柳公权",R.drawable.lgq,lgqInfo),
        CalligraphyInfo("怀素",R.drawable.hs,hsInfo),
        CalligraphyInfo("欧阳询",R.drawable.oyx ,oyxInfo),
        CalligraphyInfo("米芾",R.drawable.mf,mfInfo),
        CalligraphyInfo("黄庭坚",R.drawable.htj,htjInfo),
        CalligraphyInfo("智永",R.drawable.zy,zyInfo),
        CalligraphyInfo("赵孟頫",R.drawable.zmbrs,zmbrsInfo),
    )

    val infoArrayList = ArrayList<CalligraphyInfo>()

    fun initData(){
        infoArrayList.clear()
        for (item in infoList){
            infoArrayList.add(item)
        }
    }
    fun initAuthor(){
        infoArrayList.clear()
        for (item in authorList){
            infoArrayList.add(item)
        }
    }

    fun initHitory(){
        infoArrayList.clear()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "这是名家"
    }
    val text: LiveData<String> = _text
}


class InfoAdapter(val context: Context, val InfoList: List<CalligraphyInfo>) :  RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val typeNameTextView : TextView = view.findViewById(R.id.infoName)
        val image : ImageView = view.findViewById(R.id.infoImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.infoitemlayout, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val info = InfoList[position]
            val intent = Intent(context, InfoItemActivity::class.java).apply {
                putExtra("imageid", info.imageSrc)
                putExtra("name", info.typeName)
                putExtra("detail", info.infoStatement)

            }
            context.startActivity(intent)
        }
        return holder

    }

    override fun getItemCount() = InfoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = InfoList[position]
        holder.typeNameTextView.text = info.typeName
        Glide.with(context).load(info.imageSrc).into(holder.image)
    }
}
class CalligraphyInfo(val typeName : String , val imageSrc :Int , val infoStatement :String)