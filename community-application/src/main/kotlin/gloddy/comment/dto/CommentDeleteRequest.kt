package gloddy.comment.dto

import gloddy.comment.CommentId
import gloddy.user.UserId

data class CommentDeleteRequest(
    val commentId: CommentId,
    val userId: UserId
)