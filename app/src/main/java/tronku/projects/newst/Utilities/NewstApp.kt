package tronku.projects.newst.Utilities

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.core.content.getSystemService

class NewstApp: Application() {

    companion object {
        private lateinit var pref: SharedPreferences
        private lateinit var INSTANCE: NewstApp

        fun getInstance(): NewstApp = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        pref = PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun getPref(): SharedPreferences = pref

    fun saveString(key: String, data: String) =
        pref.edit { putString(key, data) }

    fun saveInt(key: String, data: Int) =
        pref.edit { putInt(key, data) }

    fun saveBoolean(key: String, data: Boolean) =
        pref.edit { putBoolean(key, data) }

    fun clearData(key: String) =
        pref.edit { remove(key) }

    fun isConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}