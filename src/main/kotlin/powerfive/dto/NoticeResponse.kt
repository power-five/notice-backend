package powerfive.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class NoticeResponse(
    val noticeId: Long,
    val title: String,
    val description: String,
    val writer: MemberResponse,
    val images: List<ImageResponse>,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime
)
