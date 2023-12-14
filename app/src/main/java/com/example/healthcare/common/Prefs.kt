package com.example.healthcare.common

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
    fun writeToken(token: String){
        with(prefs.edit()){
            putString("token",token)
            apply()
        }
    }

    fun writeIsLogIn(isLogIn: Boolean){
        with(prefs.edit()){
            putBoolean("login",isLogIn)
            apply()
        }
    }

    fun writeIsFirstLaunched(isFirstLaunched: Boolean){
        with(prefs.edit()){
            putBoolean("firstLaunch",isFirstLaunched)
            apply()
        }
    }
    fun writeIsProfileCreated(isProfileCreate: Boolean){
        with(prefs.edit()){
            putBoolean("profile",isProfileCreate)
            apply()
        }
    }

    fun getIsProfileCreate() : Boolean {
        return prefs.getBoolean("profile",false)
    }

    fun getIsLogIn() : Boolean {
        return prefs.getBoolean("login",false)
    }
    fun writeUserId(id: String) {
        with(prefs.edit()){
            putString("id",id)
            apply()
        }
    }

    fun writeRole(role: String) {
        with(prefs.edit()){
            putString("role",role)
            apply()
        }
    }

    fun getRole() : String? {
        return prefs.getString("role","")
    }

    fun getIsFirstLaunched() : Boolean {
        return prefs.getBoolean("firstLaunch",false)
    }
    fun getUserId() : String? {
        return prefs.getString("id","")
    }

    fun getToken(): String? {
        return prefs.getString("token", "")
    }

}