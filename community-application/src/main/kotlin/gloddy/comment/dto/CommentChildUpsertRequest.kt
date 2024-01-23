package gloddy.comment.dto

import gloddy.article.port.`in`.dto.command.CommentStatus
import gloddy.core.CommentId

data class CommentChildUpsertRequest(
    val commentId: CommentId,
    val status: CommentStatus
)
