package powerfive.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import powerfive.entity.ImageEntity

@Component
class ImageDao(
    private val jdbcTemplate: JdbcTemplate,
) {
    private val simpleJdbcInsert: SimpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("image")
        .usingGeneratedKeyColumns("id")

    fun insert(imageEntity: ImageEntity): Long {
        val map: Map<String, Any> = mapOf(
            "notice_id" to imageEntity.noticeId,
            "image_url" to imageEntity.imageUrl
        )
        return simpleJdbcInsert.executeAndReturnKey(map).toLong()
    }

    fun findByNoticeId(noticeId: Long): List<ImageEntity> {
        val sql = "SELECT * FROM image WHERE notice_id = ?"
        return jdbcTemplate.query(sql, mapper, noticeId)
    }

    fun deleteByNoticeId(id: Long) {
        val sql = "DELETE FROM image where notice_id = ?"
        jdbcTemplate.update(sql, id)
    }

    companion object {
        val mapper = RowMapper<ImageEntity> { resultSet, rowId ->
            ImageEntity(
                resultSet.getLong("id"),
                resultSet.getLong("notice_id"),
                resultSet.getString("image_url")
            )
        }
    }
}
