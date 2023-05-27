package powerfive.mapper

import powerfive.domain.Notice
import powerfive.dto.ImageResponse
import powerfive.dto.NoticeResponse
import powerfive.entity.MemberEntity
import powerfive.entity.NoticeEntity

class NoticeMapper {

    companion object {
        fun toNotice(entity: NoticeEntity, memberEntity: MemberEntity, images: List<String>): Notice {
            return Notice(entity.id, entity.title, entity.description, entity.createAt.toLocalDateTime(), MemberMapper.toMember(memberEntity), images)
        }

        fun toResponse(notice: Notice): NoticeResponse {
            return NoticeResponse(notice.id, notice.title, notice.description, MemberMapper.toResponse(notice.writer), notice.images.map { ImageResponse(it) }, notice.createAt)
        }
    }
}
