package junsulime.androidtutorial.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import junsulime.androidtutorial.R
import junsulime.androidtutorial.api.postApi
import junsulime.androidtutorial.models.PostUpdateDTO
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(toolbar)
    }

    private fun postOnBoard() {
        postApi.postOnBoard(PostUpdateDTO(
                postTitle.text.toString(),
                postContents.text.toString()
        )).enqueue(object : Callback<PostUpdateDTO> {
            override fun onResponse(call: Call<PostUpdateDTO>, response: Response<PostUpdateDTO>) {
                handlePostOnBoard(response.code())
            }

            override fun onFailure(call: Call<PostUpdateDTO>, t: Throwable) {

            }
        })
        Toast.makeText(this, "Post on board", Toast.LENGTH_SHORT).show()
    }

    fun handlePostOnBoard(code: Int) {
        if (code != 201) {
            return
        }

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.post_action -> postOnBoard()
        }

        return super.onOptionsItemSelected(item)
    }
}