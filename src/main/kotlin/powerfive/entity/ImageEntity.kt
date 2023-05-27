package powerfive.entity

data class ImageEntity(
        val id: Long,
        val noticeId: Long,
        val imageUrl: String
) {
    constructor(noticeId: Long, imageUrl: String) : this(0, noticeId, imageUrl)
}
