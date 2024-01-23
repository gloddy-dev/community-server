package gloddy.comment.dto

import gloddy.core.ArticleId
import gloddy.core.CommentId
import gloddy.core.UserId

data class CommentDeleteRequest(
    val commentId: CommentId,
    val userId: UserId,
    val articleId: ArticleId
)