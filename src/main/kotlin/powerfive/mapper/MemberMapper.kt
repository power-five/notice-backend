package powerfive.mapper

import powerfive.domain.Member
import powerfive.dto.MemberResponse
import powerfive.entity.MemberEntity

class MemberMapper {
    companion object {
        fun toResponse(member: Member): MemberResponse {
            return MemberResponse(member.nickname, member.imageUrl)
        }

        fun toMember(memberEntity: MemberEntity): Member {
            return Member(
                memberEntity.id,
                memberEntity.email,
                memberEntity.nickname,
                memberEntity.password,
                memberEntity.role,
                memberEntity.imageUrl
            )
        }
    }
}
