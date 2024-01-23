package gloddy.article.service

import gloddy.article.port.`in`.ArticleQueryUseCase
import gloddy.article.port.`in`.dto.command.ArticleDetailGetRequest
import gloddy.article.port.`in`.dto.command.ArticleDetailPageGetRequest
import gloddy.article.port.`in`.dto.read.ArticleDetailResponse
import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.core.dto.PageResponse
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Service

@Service
class ArticleQueryService(
    private val userQueryPort: UserQueryPort,
    private val articleQueryPersistencePort: ArticleQueryPersistencePort,
) : ArticleQueryUseCase {

    override fun getArticleDetailPage(request: ArticleDetailPageGetRequest): PageResponse<ArticleDetailResponse> {
        val articleDetailUnitPage = articleQueryPersistencePort.findArticleDetailUnitPageByCategoryId(
            categoryId = request.categoryId,
            userId = request.userId,
            size = request.size,
            page = request.page,
            order = request.order
        )

        val userPreviewUnits = userQueryPort.getUserPreviewUnits(
            userIds = articleDetailUnitPage.contents.map { it.userId }.toSet()
        )

        return PageResponse(
            totalCount = articleDetailUnitPage.totalCount,
            currentCount = articleDetailUnitPage.currentCount,
            totalPage = articleDetailUnitPage.totalPage,
            currentPage = articleDetailUnitPage.currentPage,
            contents = articleDetailUnitPage.contents
                .map {
                    ArticleDetailResponse(
                        article = it,
                        writer = userPreviewUnits[it.userId]!!
                    )
                }
        )
    }

    override fun getArticleDetail(request: ArticleDetailGetRequest): ArticleDetailResponse {
        val articleDetailUnit = articleQueryPersistencePort.findArticleDetailUnitById(
            id = request.id,
            userId = request.userId
        )
        val userPreviewUnit = userQueryPort.getUserPreviewUnit(articleDetailUnit.userId)
        return ArticleDetailResponse(
            article = articleDetailUnit,
            writer = userPreviewUnit
        )
    }
}