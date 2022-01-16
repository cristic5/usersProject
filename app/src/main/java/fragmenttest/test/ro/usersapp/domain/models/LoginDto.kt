package fragmenttest.test.ro.usersapp.domain.models

data class LoginDto (

	val uuid : String,
	val username : String,
	val password : String,
	val salt : String,
	val md5 : String,
	val sha1 : String,
	val sha256 : String
)