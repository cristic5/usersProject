package fragmenttest.test.ro.usersapp.presentation

data class UserModel(
    val id: Long,
    val name: String,
    val age: Int,
    val country: String,
    val time: Long,
    val imageUrl: String?,
    val shouldShowAttachment: Boolean
)