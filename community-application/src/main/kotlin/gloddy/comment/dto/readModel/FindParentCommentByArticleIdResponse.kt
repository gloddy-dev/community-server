package gloddy.comment.dto.readModel

import gloddy.user.port.`in`.dto.UserPreviewUnit

data class FindParentCommentByArticleIdResponse(
    val comment: ParentCommentUnit,
    val writer: UserPreviewUnit
)