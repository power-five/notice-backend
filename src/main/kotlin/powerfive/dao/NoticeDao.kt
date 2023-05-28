package powerfive.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import powerfive.entity.NoticeEntity

@Component
class NoticeDao(
        private val jdbcTemplate: JdbcTemplate,
) {
    private val simpleJdbcInsert: SimpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate)
            .withTableName("notice")
            .usingGeneratedKeyColumns("id")

    fun findById(id: Long): NoticeEntity {
        val sql = "select * from notice where id = ?"
        return jdbcTemplate.queryForObject(sql, mapper, id) ?: throw NoSuchElementException("게시물을 찾을 수 없습니다.")
    }

    fun findAll(): List<NoticeEntity> {
        val sql = "select * from notice"
        return jdbcTemplate.query(sql, mapper)
    }

    fun insert(noticeEntity: NoticeEntity): Long {
        val map: Map<String, Any> = mapOf(
                "title" to noticeEntity.title,
                "description" to noticeEntity.description,
                "created_at" to noticeEntity.createAt,
                "writer_id" to noticeEntity.writerId
        )
        return simpleJdbcInsert.executeAndReturnKey(map).toLong()
    }

    fun update(noticeEntity: NoticeEntity) {
        val sql = "UPDATE notice SET title = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, noticeEntity.title, noticeEntity.description, noticeEntity.id);
    }

    fun deleteById(id: Long) {
        val sql = "delete from notice where id = ?"
        jdbcTemplate.update(sql, id)
    }

    companion object {
        val mapper = RowMapper<NoticeEntity> { resultSet, rowId ->
            NoticeEntity(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getLong("writer_id")
            )
        }
    }
}
