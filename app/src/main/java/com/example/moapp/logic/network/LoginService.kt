package com.example.moapp.logic.network


import com.example.moapp.logic.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET("/login")
    fun loginAccess(@Query("userphone") userphone : String , @Query("password") password : String) : Call<String>
    @GET("/register")
    fun registerAccess(@Query("userphone") userphone : String ,@Query("username") username : String , @Query("password") password : String) : Call<String>

}