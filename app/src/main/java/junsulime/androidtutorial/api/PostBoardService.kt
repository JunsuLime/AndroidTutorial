package junsulime.androidtutorial.api

import junsulime.androidtutorial.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

val postApi by lazy { PostBoardService.create() }

interface PostBoardService {

    @POST("/users/sign")
    fun sign(@Body user: User): Call<User>

    companion object {
        fun create(): PostBoardService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://android-tutorial-server.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PostBoardService::class.java)
        }
    }
}