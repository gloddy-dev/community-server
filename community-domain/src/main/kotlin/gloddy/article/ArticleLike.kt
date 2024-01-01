package gloddy.article

import gloddy.core.UserId

data class ArticleLike(
    val userId: UserId,
    val article: Article,
    val id: Long? = null,
)
