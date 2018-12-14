package junsulime.androidtutorial.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.lang.ref.WeakReference

class DefaultPrefHelper private constructor(context: Context) {
    private val contextRef: WeakReference<Context> = WeakReference(context)

    private fun sharedPreference(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(contextRef.get())
    }

    fun getStringSet(key: String): MutableSet<String> {
        return sharedPreference().getStringSet(key, mutableSetOf()) ?: mutableSetOf()
    }

    fun getString(key: String): String? {
        return sharedPreference().getString(key, null)
    }

    fun setStringSet(key: String, value: Set<String>) {
        sharedPreference().edit().run {
            putStringSet(key, value)
            apply()
        }
    }

    fun setString(key: String, value: String) {
        sharedPreference().edit().run {
            putString(key, value)
            apply()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DefaultPrefHelper? = null

        fun instance(): DefaultPrefHelper {
            if (INSTANCE == null) {
                throw IllegalStateException()
            }
            return INSTANCE as DefaultPrefHelper
        }

        fun init(context: Context) {
            if (context !is Application) {
                throw IllegalArgumentException()
            }

            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DefaultPrefHelper(context).also { INSTANCE = it }
            }
        }
    }
}