package gloddy.like.port.out

import gloddy.comment.CommentId
import gloddy.like.CommentLike
import gloddy.user.UserId

interface CommentLikeQueryPort {
    fun findByCommentIdAndUserId(commentId: CommentId, userId: UserId): CommentLike
}