package powerfive.domain

data class Member(
        val id: Long,
        val email: String,
        val password: String,
        val role: Role,
        val imageUrl: String
)
