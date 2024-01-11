package gloddy.comment.port.out

import gloddy.comment.CommentId
import gloddy.comment.CommentLike
import gloddy.user.UserId

interface CommentLikeQueryPort {
    fun findByCommentIdAndUserId(commentId: CommentId, userId: UserId): CommentLike?
}