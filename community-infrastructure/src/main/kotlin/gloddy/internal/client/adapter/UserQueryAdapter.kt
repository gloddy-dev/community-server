package gloddy.internal.client.adapter

import gloddy.internal.client.UserQueryClient
import gloddy.internal.client.payload.toResponse
import gloddy.user.port.`in`.dto.UserPreviewUnit
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Component

@Component
class UserQueryAdapter(
    private val userQueryClient: UserQueryClient,
) : UserQueryPort {

    override fun getUserPreviewUnit(userId: Long): UserPreviewUnit {
        return userQueryClient.getUserPreview(userId).toResponse()
    }

    override fun getUserPreviewUnits(userIds: Set<Long>): Map<Long, UserPreviewUnit> {
        return userQueryClient.getUserPreviews(userIds)
            .users.associateBy { it.id }
            .mapValues { it.value.toResponse() }
    }
}