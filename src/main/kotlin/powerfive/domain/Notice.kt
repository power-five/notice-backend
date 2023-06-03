package powerfive.domain

import java.time.LocalDateTime

data class Notice(
    val id: Long,
    private var _title: String,
    private var _description: String,
    val createAt: LocalDateTime,
    val writer: Member,
    private var _images: List<String>
) {
    val title: String
        get() = _title

    val description: String
        get() = _description

    val images: List<String>
        get() = _images

    fun update(title: String, description: String, images: List<String>) {
        this._title = title
        this._description = description
        this._images = images
    }
}
