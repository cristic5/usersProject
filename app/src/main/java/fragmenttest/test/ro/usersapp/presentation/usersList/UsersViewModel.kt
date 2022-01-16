package fragmenttest.test.ro.usersapp.presentation.usersList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fragmenttest.test.ro.usersapp.domain.UsersRepository
import fragmenttest.test.ro.usersapp.presentation.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    var page = 0

    private val _emptyList = MutableStateFlow(true)
    val emptyList = _emptyList.asStateFlow()

    private val _showLoading = MutableStateFlow(true)
    val showLoading = _showLoading.asStateFlow()

    private val _usersList = MutableStateFlow<List<UserModel>>(emptyList())
    val usersList = _usersList.asStateFlow()

    fun getUsers() {
        usersRepository.getUsers(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    val usersItems = it.body()?.results?.map { userDto ->
                        userDto.toUserModel()
                    } ?: emptyList()

                    _usersList.value = usersItems
                } else {
                    //show error
                }
            }, {
                //show error
                it.printStackTrace()
            })
    }

}