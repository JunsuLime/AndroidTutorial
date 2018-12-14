package junsulime.androidtutorial

import android.app.Application
import junsulime.androidtutorial.common.DefaultPrefHelper

class AndroidTutorial: Application() {

    override fun onCreate() {
        super.onCreate()
        DefaultPrefHelper.init(this)
    }
}