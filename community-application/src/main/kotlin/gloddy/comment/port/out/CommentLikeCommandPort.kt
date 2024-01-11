package gloddy.like.port.out

import gloddy.like.CommentLike

interface CommentLikeCommandPort {
    fun create(commentLike: CommentLike): CommentLike
    fun delete(commentLike: CommentLike)
}