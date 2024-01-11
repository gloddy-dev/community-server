package gloddy.comment.dto

import gloddy.comment.CommentId
import gloddy.user.UserId

data class CommentLikeCreateRequest(
    val userId: UserId,
    val commentId: CommentId
)