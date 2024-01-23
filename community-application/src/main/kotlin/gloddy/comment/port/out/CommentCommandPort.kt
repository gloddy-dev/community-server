package gloddy.comment.port.out

import gloddy.comment.Comment
import gloddy.comment.CommentLike

interface CommentCommandPort {
    fun save(comment: Comment): Comment
    fun delete(comment: Comment)
    fun upsertLike(commentLike: CommentLike, comment: Comment)
}
