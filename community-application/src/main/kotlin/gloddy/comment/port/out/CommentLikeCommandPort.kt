package gloddy.comment.port.out

import gloddy.comment.CommentLike

interface CommentLikeCommandPort {
    fun save(commentLike: CommentLike): CommentLike
    fun delete(commentLike: CommentLike)
}