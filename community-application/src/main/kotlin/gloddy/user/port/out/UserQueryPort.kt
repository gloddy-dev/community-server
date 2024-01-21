package gloddy.user.port.out

import gloddy.user.port.`in`.dto.UserPreviewUnit

interface UserQueryPort {
    fun getUserPreviewUnit(userId: Long): UserPreviewUnit
    fun getUserPreviewUnits(userIds: Set<Long>): Map<Long, UserPreviewUnit>
}