package powerfive.dto

data class NoticeRequest(
        val title: String,
        val description: String,
        val writerId: Long,
        val images: List<ImageRequest>
)
