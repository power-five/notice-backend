package powerfive.domain

import java.time.LocalDateTime

data class Notice(
        val id: Long,
        private var title: String,
        private var description: String,
        val createAt: LocalDateTime,
        val writerId: Long,
        val images: List<String>
) {
    fun update(title: String, description: String) {
        this.title = title
        this.description = description
    }
}
