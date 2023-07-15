package com.gabniel.elibrary.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppManager @Inject constructor(@ApplicationContext context: Context) {

    private val preference = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE)
    val editor = preference.edit()

    fun login(username: String) {
        editor.putString(PREFS_USERNAME, username).commit()
        editor.apply()
    }

    fun isLogin(): String? {
        return preference.getString(PREFS_USERNAME, "")
    }

    fun logout() {
        editor.putString(PREFS_USERNAME, "")
        editor.apply()
    }

    companion object {
        const val PREFS_APP = "ELibrary"
        const val PREFS_USERNAME = "USERNAME"

    }
}