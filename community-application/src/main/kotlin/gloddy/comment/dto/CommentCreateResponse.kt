package gloddy.comment.dto

import gloddy.comment.Comment

data class CommentCreateResponse(
    val commentId: Long
) {
    constructor(comment: Comment) : this(
        commentId = comment.id!!.value
    )
}