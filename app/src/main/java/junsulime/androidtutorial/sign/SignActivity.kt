package junsulime.androidtutorial.sign

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import junsulime.androidtutorial.R
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.common.DefaultPrefHelper
import junsulime.androidtutorial.home.HomeActivity
import junsulime.androidtutorial.models.User
import kotlinx.android.synthetic.main.activity_sign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val SIGN_PREFERENCE = "sign_preference"

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        signButton.setOnClickListener {
            onClickSignButton()
        }
    }

    private fun onClickSignButton() {
        postApi.sign(User(signEditText.text.toString()))
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        handleSign(response.code(), response.body())
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@SignActivity, "Failure", Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun handleSign(code: Int, user: User?) {
        if (code != 201) {
            Toast.makeText(this, "Failed to sign", Toast.LENGTH_SHORT).show()
            return
        }

        DefaultPrefHelper.instance().setBoolean(SIGN_PREFERENCE, true)
        startActivity(Intent(this, HomeActivity::class.java))
    }
}