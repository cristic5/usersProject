package fragmenttest.test.ro.usersapp.domain

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private const val API_URL = "https://randomuser.me/"

    val service: WebApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(WebApi::class.java)

}