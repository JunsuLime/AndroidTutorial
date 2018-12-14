package junsulime.androidtutorial.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.models.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postApi.home(0).enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                Toast.makeText(this@HomeActivity, "user name: ${response.body()?.user?.name ?: "No User"}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "What the ..", Toast.LENGTH_SHORT).show()
            }
        })
    }
}