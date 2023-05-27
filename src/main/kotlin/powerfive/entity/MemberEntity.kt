package powerfive.entity

import powerfive.Role

data class MemberEntity(
        val id: Long,
        val email: String,
        val password: String,
        val role: Role,
        val imageUrl: String
)
