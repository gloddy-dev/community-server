package gloddy.like

import gloddy.comment.ArticleId
import gloddy.comment.CommentId
import gloddy.user.UserId

data class CommentLike(
    val id: Long,
    val userId: UserId,
    val commentId: CommentId
)

data class ArticleLike(
    val id: Long,
    val userId: UserId,
    val articleId: ArticleId
)