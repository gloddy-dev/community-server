package gloddy.comment.dto

import gloddy.core.ArticleId
import gloddy.core.CommentId
import gloddy.core.UserId

data class ParentCommentCreateRequest(
    val userId: UserId,
    val articleId: ArticleId,
    val content: String
)

data class ChildCommentCreateRequest(
    val userId: UserId,
    val articleId: ArticleId,
    val parentCommentId: CommentId,
    val content: String
)

