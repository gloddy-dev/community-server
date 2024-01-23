package gloddy.comment.port.out

import gloddy.comment.CommentLike
import gloddy.core.CommentId
import gloddy.core.UserId

interface CommentLikeQueryPort {
    fun findByCommentIdAndUserIdOrNull(commentId: CommentId, userId: UserId): CommentLike?
}