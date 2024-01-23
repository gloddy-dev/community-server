package gloddy.comment.dto

import gloddy.core.CommentId
import gloddy.core.UserId

data class CommentLikeUpsertRequest(
    val userId: UserId,
    val commentId: CommentId
)