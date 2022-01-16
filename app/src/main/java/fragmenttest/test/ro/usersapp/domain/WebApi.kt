package fragmenttest.test.ro.usersapp.domain

import fragmenttest.test.ro.usersapp.domain.models.RandomUserDto
import fragmenttest.test.ro.usersapp.domain.models.UserDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("api/")
    fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String,
    ): Observable<Response<RandomUserDto>>

}