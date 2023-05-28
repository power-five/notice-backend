package powerfive.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import powerfive.domain.Notice
import powerfive.dto.NoticeRequest
import powerfive.dto.NoticeResponse
import powerfive.dto.NoticeUpdateRequest
import powerfive.dto.NoticesResponse
import powerfive.mapper.NoticeMapper
import powerfive.repository.NoticeRepository

@Service
class NoticeService(val noticeRepository: NoticeRepository) {

    fun findAll(): NoticesResponse {
        return NoticesResponse(noticeRepository.findAll().map { NoticeMapper.toResponse(it) }.toList())
    }

    fun findById(id: Long): NoticeResponse {
        return NoticeMapper.toResponse(noticeRepository.findById(id))
    }

    @Transactional
    fun create(noticeRequest: NoticeRequest): Long {
        return noticeRepository.save(noticeRequest)
    }

    fun update(id: Long, request: NoticeUpdateRequest): NoticeResponse {
        val notice = noticeRepository.findById(id);
        notice.update(request.title, request.description, request.images.map { it.imageUrl }.toList());
        val updated: Notice = noticeRepository.update(notice);
        return NoticeMapper.toResponse(updated);
    }

    fun deleteById(id: Long) {
        noticeRepository.deleteById(id);
    }
}
