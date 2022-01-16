package fragmenttest.test.ro.usersapp.presentation.usersList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel : ViewModel() {

    init {
        getUsers()
    }

    private val _emptyList = MutableStateFlow(true)
    val emptyList = _emptyList.asStateFlow()

    private val _showLoading = MutableStateFlow(true)
    val showLoading = _showLoading.asStateFlow()

    fun getUsers() {

    }

}