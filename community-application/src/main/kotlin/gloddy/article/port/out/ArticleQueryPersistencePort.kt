package gloddy.article.port.out

import gloddy.article.Article
import gloddy.article.port.`in`.dto.read.ArticleDetailUnit
import gloddy.article.port.`in`.ArticleOrder
import gloddy.core.dto.PageResponse

interface ArticleQueryPersistencePort {
    fun findById(id: Long): Article

    fun findArticleDetailUnitPageByCategoryId(
        categoryId: Long? = null,
        userId: Long,
        size: Int,
        page: Int,
        order: ArticleOrder,
    ): PageResponse<ArticleDetailUnit>

    fun findArticleDetailUnitById(
        id: Long,
        userId: Long
    ): ArticleDetailUnit
}