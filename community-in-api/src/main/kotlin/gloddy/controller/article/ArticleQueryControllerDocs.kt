package gloddy.controller.article

import gloddy.article.port.`in`.ArticleOrder
import gloddy.article.port.`in`.dto.read.ArticleDetailResponse
import gloddy.core.dto.PageResponse
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "게시글")
interface ArticleQueryControllerDocs {

    @Operation(summary = "게시글 페이징 조회")
    @ApiResponse(responseCode = "200", description = "게시글 페이징 조회 성공")
    fun getArticlePage(
        @Parameter(hidden = true) userId: Long,
        @RequestParam(name = "categoryId", required = false) categoryId: Long?,
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "5") size: Int,
        @RequestParam(name = "order", required = false, defaultValue = "LATEST") order: ArticleOrder
    ) : ResponseEntity<CommunityApiResponse<PageResponse<ArticleDetailResponse>>>
}