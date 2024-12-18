package com.example.moapp.logic.model

data class ForumItemInfo(
    // val user: User,  // 包含用户名和头像 调试先不用
    val userName : String,
    val userHeadshot : Int,
    val title : String,
    val content : String
)