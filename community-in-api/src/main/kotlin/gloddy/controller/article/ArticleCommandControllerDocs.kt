package gloddy.controller.article

import gloddy.article.port.`in`.dto.command.ArticleCreateRequest
import gloddy.article.port.`in`.dto.read.ArticleCreateResponse
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "게시글")
interface ArticleCommandControllerDocs {

    @Operation(summary = "게시글 생성")
    @ApiResponse(responseCode = "201", description = "게시글 생성 성공")
    fun create(@Parameter(hidden = true) userId: Long, @RequestBody command: ArticleCreateRequest)
    : ResponseEntity<CommunityApiResponse<ArticleCreateResponse>>

    @Operation(summary = "게시글 삭제")
    @ApiResponse(responseCode = "204", description = "게시글 삭제 성공")
    fun delete(@Parameter(hidden = true) userId: Long, @PathVariable("articleId") articleId: Long)
    : ResponseEntity<CommunityApiResponse<Nothing>>

    @Operation(summary = "게시글 좋아요 및 좋아요 취소")
    @ApiResponse(responseCode = "204", description = "게시글 좋아요 및 좋아요 취소 성공")
    fun like(@Parameter(hidden = true) userId: Long, @PathVariable("articleId") articleId: Long)
    : ResponseEntity<CommunityApiResponse<Nothing>>
}