package junsulime.androidtutorial.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.common.DefaultPrefHelper
import junsulime.androidtutorial.models.HomeResponse
import junsulime.androidtutorial.sign.SIGN_PREFERENCE
import junsulime.androidtutorial.sign.SignActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postApi.home(0).enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val user = response.body()?.user
                if (user == null) {
                    signOut()
                    return
                }
                Toast.makeText(this@HomeActivity, "user name: ${response.body()?.user?.name ?: "No User"}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "What the ..", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun signOut() {
        DefaultPrefHelper.instance().setBoolean(SIGN_PREFERENCE, false);
        startActivity(Intent(this, SignActivity::class.java))
        finish()
    }
}