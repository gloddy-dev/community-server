package gloddy.comment.dto.readModel

import gloddy.user.port.`in`.dto.UserPreviewUnit

data class FindChildCommentByParentIdResponse(
    val childComment: ChildCommentUnit,
    val writer: UserPreviewUnit
)