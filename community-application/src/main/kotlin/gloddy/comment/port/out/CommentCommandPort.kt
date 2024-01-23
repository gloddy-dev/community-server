package gloddy.comment.port.out

import gloddy.comment.Comment

interface CommentCommandPort {
    fun save(comment: Comment): Comment
    fun delete(comment: Comment)
}
