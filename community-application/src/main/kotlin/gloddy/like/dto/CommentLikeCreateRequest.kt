package gloddy.like.dto

import gloddy.comment.CommentId
import gloddy.user.UserId

data class CommentLikeCreateRequest(
    val userId: UserId,
    val commentId: CommentId
)