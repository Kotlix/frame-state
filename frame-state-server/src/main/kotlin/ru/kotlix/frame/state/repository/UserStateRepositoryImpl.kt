package ru.kotlix.frame.state.repository

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.kotlix.frame.state.service.dto.UserState
import java.time.OffsetDateTime

@Repository
class UserStateRepositoryImpl(
    private val npJdbc: NamedParameterJdbcTemplate,
) : UserStateRepository {
    override fun findStatusByUserId(userId: Long): UserState? {
        return npJdbc.query(
            "SELECT user_id, online, last_active FROM user_state WHERE user_id = :user_id;",
            mapOf("user_id" to userId),
        ) { rs, _ ->
            UserState(
                userId = rs.getLong("user_id"),
                online = rs.getBoolean("online"),
                lastActive = rs.getObject("last_active", OffsetDateTime::class.java),
            )
        }.firstOrNull()
    }

    override fun updateStatusByUserId(
        userId: Long,
        online: Boolean,
    ) {
        npJdbc.update(
            """
            insert into user_state
            (user_id, online, last_active)
            values
            (:user_id, :online, :last_active)
            on conflict (user_id) do
            update set online = :online, last_active = :last_active;
            """.trimIndent(),
            mapOf(
                "user_id" to userId,
                "online" to online,
                "last_active" to OffsetDateTime.now(),
            ),
        )
    }
}
