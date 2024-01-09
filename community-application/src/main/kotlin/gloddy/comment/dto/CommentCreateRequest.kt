package gloddy.comment.dto

import gloddy.comment.ArticleId
import gloddy.comment.CommentId
import gloddy.user.UserId

data class ParentCommentCreateDto(
    val userId: UserId,
    val articleId: ArticleId,
    val content: String
)

data class ChildCommentCreateDto(
    val userId: UserId,
    val articleId: ArticleId,
    val parentCommentId: CommentId,
    val content: String
)

