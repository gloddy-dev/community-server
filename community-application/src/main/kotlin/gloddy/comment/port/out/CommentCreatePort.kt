package gloddy.comment.port.out

import gloddy.comment.Comment

interface CommentCreatePort {
    fun create(comment: Comment)
}