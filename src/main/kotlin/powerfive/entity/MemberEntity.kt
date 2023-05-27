package powerfive.entity

import powerfive.domain.Role

data class MemberEntity(
        val id: Long,
        val email: String,
        val nickname: String,
        val password: String,
        val role: Role,
        val imageUrl: String
)
