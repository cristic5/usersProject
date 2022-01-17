package fragmenttest.test.ro.usersapp.presentation.usersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fragmenttest.test.ro.usersapp.domain.UsersRepository
import fragmenttest.test.ro.usersapp.presentation.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var pageNumber = 1

    val showEmptyList = MutableLiveData(false)
    val showLoading = MutableLiveData(false)
    val showError = MutableLiveData(false)
    val resetAdapter = MutableLiveData(false)

    private val _usersList = MutableStateFlow<List<UserModel>>(emptyList())
    val usersList = _usersList.asStateFlow()

    fun getUsers() {
        if (pageNumber >= 4 || showLoading.value == true) {
            return
        }

        showLoading.value = true

        val pageToLoad = pageNumber
        pageNumber++

        compositeDisposable.add(
            usersRepository.getUsers(pageToLoad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        val usersItems = it.body()?.results?.map { userDto ->
                            userDto.toUserModel()
                        } ?: emptyList()

                        if (pageNumber == 1 && usersItems.isEmpty()) {
                            showEmptyList.value = false
                        } else {
                            _usersList.value = usersItems
                        }
                    } else {
                        showError.value = true
                    }
                    showLoading.value = false
                }, {
                    showError.value = true
                    showLoading.value = false
                    it.printStackTrace()
                })
        )
    }

    fun resetPageAndReloadUsers() {
        pageNumber = 1
        resetAdapter.value = true
        _usersList.value = emptyList()
        getUsers()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}