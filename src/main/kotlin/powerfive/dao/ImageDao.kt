package powerfive.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import powerfive.entity.ImageEntity

@Repository
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
        val sql = "select * from image where notice_id = ?"
        return jdbcTemplate.query(sql, mapper, noticeId)
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
