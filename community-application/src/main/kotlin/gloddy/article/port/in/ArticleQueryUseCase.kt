package gloddy.article.port.`in`

import gloddy.article.port.`in`.dto.command.ArticleDetailPageGetRequest
import gloddy.article.port.`in`.dto.read.ArticleDetailResponse
import gloddy.core.dto.PageResponse

interface ArticleQueryUseCase {
    fun getArticleDetailPage(request: ArticleDetailPageGetRequest): PageResponse<ArticleDetailResponse>
}