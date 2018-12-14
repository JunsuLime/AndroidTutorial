package junsulime.androidtutorial.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import junsulime.androidtutorial.common.DefaultPrefHelper
import junsulime.androidtutorial.home.HomeActivity
import junsulime.androidtutorial.sign.SIGN_PREFERENCE
import junsulime.androidtutorial.sign.SignActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DefaultPrefHelper.instance().getBoolean(SIGN_PREFERENCE)) {
            startActivity(Intent(this, HomeActivity::class.java))
            return
        }

        startActivity(Intent(this, SignActivity::class.java))
    }
}