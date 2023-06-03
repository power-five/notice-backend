package powerfive.domain

data class Member(
    val id: Long,
    val email: String,
    val nickname: String,
    val password: String,
    val role: Role,
    val imageUrl: String
)
