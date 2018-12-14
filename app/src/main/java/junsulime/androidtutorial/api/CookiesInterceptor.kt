package junsulime.androidtutorial.api

import junsulime.androidtutorial.common.DefaultPrefHelper
import okhttp3.Interceptor
import okhttp3.Response

const val COOKIE_PREFERENCE = "api_preference"

class AddCookiesInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Get cookie from shared preference
        val cookies = DefaultPrefHelper.instance().getStringSet(COOKIE_PREFERENCE)
        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie.toString())
        }

        // Set user-agent
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")
        return chain.proceed(builder.build())
    }
}

class ReceivedCookiesInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cookies = response.headers("Set-Cookie")
        if (cookies.isEmpty()) {
            return response
        }

        // Set cookie to shared preferences
        DefaultPrefHelper.instance().setStringSet(COOKIE_PREFERENCE, cookies.toSet())
        return response
    }
}