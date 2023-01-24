package com.mt.android.util

import android.content.Context

object PreferencesSettings {

    private const val PREF_FILE = "password_manager"

    @JvmStatic
    fun saveToPref(context: Context, str: String?) {
        val sharedPref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("code", str)
        editor.apply()
    }

    @JvmStatic
    fun getCode(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val defaultValue = ""
        return sharedPref.getString("code", defaultValue)
    }
}