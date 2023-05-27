package powerfive.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import powerfive.dto.NoticeRequest
import powerfive.dto.NoticeResponse
import powerfive.dto.NoticesResponse
import powerfive.service.NoticeService
import java.net.URI

@RestController
@RequestMapping("/notice")
class NoticeController(private val noticeService: NoticeService) {

    @GetMapping
    fun findAll(): ResponseEntity<NoticesResponse> {
        val noticesResponse: NoticesResponse = noticeService.findAll()
        return ResponseEntity.ok().body(noticesResponse);
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<NoticeResponse> {
        val noticeResponse = noticeService.findById(id)
        return ResponseEntity.ok().body(noticeResponse);
    }

    @PostMapping
    fun create(@RequestBody request: NoticeRequest): ResponseEntity<Unit> {
        val noticeId: Long = noticeService.create(request)
        return ResponseEntity.created(URI("/notice/$noticeId")).build()
    }
}
