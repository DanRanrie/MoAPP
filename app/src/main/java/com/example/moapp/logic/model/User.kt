package com.example.moapp.logic.model

data class User(val userId : Long ,
                val userPhoneNumber : String ,
                val userName : String ,
                val userPassword : String ,
                val SigninDays : Long
);