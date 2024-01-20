package gloddy.controller.article

import gloddy.article.port.`in`.ArticleOrder
import gloddy.article.port.`in`.ArticleQueryUseCase
import gloddy.article.port.`in`.dto.command.ArticleDetailPageGetRequest
import gloddy.article.port.`in`.dto.read.ArticleDetailResponse
import gloddy.core.dto.PageResponse
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.CommunityApiResponse
import gloddy.response.ok
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/communities")
class ArticleQueryController(
    private val articleQueryUseCase: ArticleQueryUseCase,
) : ArticleQueryControllerDocs {

    @GetMapping("/articles")
    override fun getArticlePage(
        @RequestHeader("USER_ID") userId: Long,
        @RequestParam(name = "categoryId", required = false) categoryId: Long?,
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "5") size: Int,
        @RequestParam(name = "order", required = false, defaultValue = "LATEST") order: ArticleOrder,
    ): ResponseEntity<CommunityApiResponse<PageResponse<ArticleDetailResponse>>> {
        val data = articleQueryUseCase.getArticleDetailPage(
            ArticleDetailPageGetRequest(
                userId = userId,
                categoryId = categoryId,
                page = page,
                size = size,
                order = order
            )
        )
        return ApiResponseEntityWrapper(data).ok()
    }
}