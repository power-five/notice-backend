package powerfive.entity

import java.sql.Timestamp

data class NoticeEntity(
    val id: Long,
    val title: String,
    val description: String,
    val createAt: Timestamp,
    val writerId: Long,
) {
    constructor(title: String, description: String, createAt: Timestamp, writerId: Long) : this(
        0,
        title,
        description,
        createAt,
        writerId
    )
}
