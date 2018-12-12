package junsulime.androidtutorial.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import junsulime.androidtutorial.sign.SignActivity

class SplashActivity: AppCompatActivity() {

    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, SignActivity::class.java))
    }
}