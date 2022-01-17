package fragmenttest.test.ro.usersapp.domain.models

import fragmenttest.test.ro.usersapp.presentation.models.UserModel
import java.text.SimpleDateFormat
import java.util.*


data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val dob: DobDto,
    val registered: RegisteredDto,
    val phone: String,
    val cell: String,
    val id: IdDto,
    val picture: PictureDto,
    val nat: String
) {
    fun toUserModel(): UserModel {
        val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        return UserModel(
            id = id.value,
            name = "${name.first} ${name.last}",
            age = dob.age,
            country = nat,
            time = time.parse(registered.date),
            imageUrl = if (phone.contains("5")) null else picture.thumbnail, //random -> test name initials
            shouldShowAttachment = gender == "female" //random -> test attachment icon
        )
    }
}