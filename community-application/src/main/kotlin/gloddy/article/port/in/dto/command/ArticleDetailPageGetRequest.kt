package gloddy.article.port.`in`.dto.command

import gloddy.article.port.`in`.ArticleOrder

data class ArticleDetailPageGetRequest(
    val categoryId: Long?,
    val userId: Long,
    val size: Int,
    val page: Int,
    val order: ArticleOrder
)