package gloddy.comment.dto

import gloddy.comment.ArticleId
import gloddy.user.UserId

data class CommentGetRequest(
    val userId: UserId,
    val articleId: ArticleId
)