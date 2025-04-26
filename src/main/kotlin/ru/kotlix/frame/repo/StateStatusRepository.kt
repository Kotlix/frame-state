package ru.kotlix.frame.repo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
class StateStatusRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    fun findStatusByUserId(userId: Long): Boolean? {
        return jdbcTemplate.query(
            "SELECT online FROM user_state WHERE user_id = ?",
            arrayOf(userId)
        ) { rs, _ ->
            rs.getBoolean("online")
        }.firstOrNull()
    }

    fun updateStatusByUserId(userId: Long, online: Boolean) {
        jdbcTemplate.update(
            "UPDATE online SET status = ? WHERE user_id = ?",
            online,
            userId
        )
    }

    fun findLastActivityByUserId(userId: Long): LocalDateTime? {
        return jdbcTemplate.query(
            "SELECT last_active FROM user_state WHERE user_id = ?",
            arrayOf(userId)
        ) { rs, _ ->
            rs.getTimestamp("last_active").toLocalDateTime()
        }.firstOrNull()
    }

    fun findAllOnlineUserId(online: Boolean): List<Long>? {
        return jdbcTemplate.queryForList(
            "SELECT last_active FROM user_state WHERE online = ?",
            Long::class.java,
            online
        )
    }
}