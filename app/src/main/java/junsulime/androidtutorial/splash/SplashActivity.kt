package junsulime.androidtutorial.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import junsulime.androidtutorial.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity() {

    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        textView.text = number.toString()

        incrementButton.setOnClickListener {
            number++
            textView.text = number.toString()
        }
    }
}