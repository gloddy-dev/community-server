package gloddy.like.dto

import gloddy.comment.CommentId
import gloddy.user.UserId

data class CommentLikeDeleteRequest(
    val commentId: CommentId,
    val userId: UserId
)