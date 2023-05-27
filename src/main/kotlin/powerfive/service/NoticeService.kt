package powerfive.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import powerfive.dto.NoticeRequest
import powerfive.dto.NoticeResponse
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
}
