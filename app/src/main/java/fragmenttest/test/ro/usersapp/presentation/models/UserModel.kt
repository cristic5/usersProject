package fragmenttest.test.ro.usersapp.presentation.models

import java.util.*

data class UserModel(
    val id: String?,
    val name: String,
    val age: Int,
    val country: String,
    val time: Date?,
    val imageUrl: String?,
    val shouldShowAttachment: Boolean
)