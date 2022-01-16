package fragmenttest.test.ro.usersapp.domain

import fragmenttest.test.ro.usersapp.domain.models.RandomUserDto
import fragmenttest.test.ro.usersapp.domain.models.UserDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class UsersRepository {

    fun getUsers(
        page: Int,
        results: Int = 20,
        seed: String = "abc"
    ): Observable<Response<RandomUserDto>> {
        return NetworkManager.service.getUsers(page, results, seed)
    }

}