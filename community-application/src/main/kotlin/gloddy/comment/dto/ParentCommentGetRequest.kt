package gloddy.comment.dto

import gloddy.core.ArticleId
import gloddy.core.UserId

data class ParentCommentGetRequest(
    val userId: UserId,
    val articleId: ArticleId
)