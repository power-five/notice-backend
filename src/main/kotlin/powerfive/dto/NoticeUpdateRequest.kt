package powerfive.dto

data class NoticeUpdateRequest(
        val title: String,
        val description: String,
        val images: List<ImageRequest>
)
