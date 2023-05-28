package powerfive.repository

import org.springframework.stereotype.Repository
import powerfive.dao.ImageDao
import powerfive.dao.MemberDao
import powerfive.dao.NoticeDao
import powerfive.domain.Notice
import powerfive.dto.NoticeRequest
import powerfive.entity.ImageEntity
import powerfive.entity.MemberEntity
import powerfive.entity.NoticeEntity
import powerfive.mapper.NoticeMapper
import java.sql.Timestamp
import java.time.LocalDateTime

@Repository
class NoticeRepository(val noticeDao: NoticeDao, val imageDao: ImageDao, val memberDao: MemberDao) {

    fun findAll(): List<Notice> {
        return noticeDao.findAll().map {
            val memberEntity: MemberEntity = memberDao.findById(it.writerId)
            val images: List<String> = imageDao.findByNoticeId(it.id).map { entity -> entity.imageUrl }
            NoticeMapper.toNotice(it, memberEntity, images)
        }
    }

    fun findById(id: Long): Notice {
        val noticeEntity: NoticeEntity = noticeDao.findById(id)
        val memberEntity: MemberEntity = memberDao.findById(noticeEntity.writerId)
        val images: List<String> = imageDao.findByNoticeId(id).map { entity -> entity.imageUrl }
        return NoticeMapper.toNotice(noticeEntity, memberEntity, images)
    }

    fun save(noticeRequest: NoticeRequest): Long {
        val noticeId: Long = noticeDao.insert(NoticeEntity(
                noticeRequest.title,
                noticeRequest.description,
                Timestamp.valueOf(LocalDateTime.now()),
                noticeRequest.writerId)
        )
        noticeRequest.images.forEach { imageDao.insert(ImageEntity(noticeId, it.imageUrl)) }

        return noticeId
    }

    fun update(notice: Notice): Notice {
        val noticeEntity: NoticeEntity = NoticeMapper.toEntity(notice)
        noticeDao.update(noticeEntity);

        imageDao.deleteByNoticeId(notice.id);
        notice.images.forEach { imageDao.insert(ImageEntity(notice.id, it)) }

        return notice;
    }

    fun deleteById(id: Long) {
        noticeDao.deleteById(id)
    }
}
