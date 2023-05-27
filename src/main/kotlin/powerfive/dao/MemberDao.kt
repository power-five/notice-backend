package powerfive.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import powerfive.domain.Role
import powerfive.entity.MemberEntity

@Repository
class MemberDao(
        private val jdbcTemplate: JdbcTemplate,
) {

    fun findById(id: Long): MemberEntity {
        val sql = "select * from member where id = ?"
        return jdbcTemplate.queryForObject(sql, mapper, id) ?: throw NoSuchElementException("유저를 찾을수 없습니다.")
    }

    companion object {
        val mapper = RowMapper<MemberEntity> { resultSet, rowId ->
            MemberEntity(
                    resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    Role.valueOf(resultSet.getString("role")),
                    resultSet.getString("image_url")
            )
        }
    }
}
