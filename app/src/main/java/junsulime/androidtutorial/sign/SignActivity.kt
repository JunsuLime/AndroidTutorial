package junsulime.androidtutorial.sign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sign = this

        postApi.sign(User("junsulime"))
                .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Toast.makeText(sign, "Success ${response.code()}", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(sign, "Success", Toast.LENGTH_LONG).show()
                    }
                })
    }
}