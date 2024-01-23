package gloddy.persistence.article.adapter

import gloddy.article.Article
import gloddy.article.ArticleNotFoundException
import gloddy.article.port.`in`.dto.read.ArticleDetailUnit
import gloddy.article.port.`in`.ArticleOrder
import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.core.dto.PageResponse
import gloddy.persistence.article.repository.ArticleJpaRepository
import gloddy.persistence.article.repository.read.toUnit
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ArticleQueryPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository,
) : ArticleQueryPersistencePort {

    override fun findById(id: Long): Article {
        return (articleJpaRepository.findByIdOrNull(id) ?: throw ArticleNotFoundException()).toDomain()
    }

    override fun findArticleDetailUnitPageByCategoryId(
        categoryId: Long?,
        userId: Long,
        size: Int,
        page: Int,
        order: ArticleOrder,
    ): PageResponse<ArticleDetailUnit> {
        return articleJpaRepository.findDetailArticlePageByCategoryId(
            categoryId = categoryId,
            userId = userId,
            pageable = PageRequest.of(page, size),
            order = order
        ).toResponse(userId)
    }

    override fun findArticleDetailUnitById(id: Long, userId: Long): ArticleDetailUnit {
        return articleJpaRepository.findDetailArticleByIdOrNull(
            id = id,
            userId = userId
        )?.toUnit(userId) ?: throw ArticleNotFoundException()
    }
}