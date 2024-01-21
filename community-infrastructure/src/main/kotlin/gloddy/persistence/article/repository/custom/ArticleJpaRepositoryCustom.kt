package gloddy.persistence.article.repository.custom

import gloddy.article.port.`in`.ArticleOrder
import gloddy.persistence.article.repository.read.ArticleDetailData
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleJpaRepositoryCustom {
    fun findDetailArticlePageByCategoryId(categoryId: Long? = null, userId: Long, pageable: Pageable, order: ArticleOrder)
    : Page<ArticleDetailData>

    fun findDetailArticleByIdOrNull(id: Long, userId: Long): ArticleDetailData?
}