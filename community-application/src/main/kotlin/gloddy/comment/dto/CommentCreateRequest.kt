package gloddy.comment.dto

import gloddy.comment.ArticleId
import gloddy.comment.CommentId
import gloddy.user.UserId

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

