package junsulime.androidtutorial.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import junsulime.androidtutorial.R
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.common.DefaultPrefHelper
import junsulime.androidtutorial.models.HomeResponse
import junsulime.androidtutorial.models.PostSummary
import junsulime.androidtutorial.models.User
import junsulime.androidtutorial.post.PostActivity
import junsulime.androidtutorial.sign.SIGN_PREFERENCE
import junsulime.androidtutorial.sign.SignActivity
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity: AppCompatActivity() {

    private val postSummaryAdapter = PostSummaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initHome()
    }

    override fun onResume() {
        super.onResume()
        requestHome(0)
    }

    private fun initHome() {
        postButton.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }
        
        postSummaryList.layoutManager = LinearLayoutManager(this)
        postSummaryList.adapter = postSummaryAdapter

        requestHome(0)
    }

    private fun requestHome(page: Int) {
        postApi.home(page).enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.code() != 200) {
                    Toast.makeText(this@HomeActivity, "What the ..", Toast.LENGTH_SHORT).show()
                    return
                }
                val user = response.body()?.user
                if (user == null) {
                    signOut()
                    return
                }

                postSummaryAdapter.resetPosts(response.body()?.posts ?: listOf())
                postSummaryAdapter.notifyDataSetChanged()
                Toast.makeText(this@HomeActivity, "user name: ${response.body()?.user?.name ?: "No User"}, posts: ${response.body()?.posts?.size}", Toast.LENGTH_SHORT).show()
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