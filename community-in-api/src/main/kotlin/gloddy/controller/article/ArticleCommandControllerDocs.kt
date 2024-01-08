package gloddy.controller.article

import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.read.ArticleIdReadData
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "게시글")
interface ArticleCommandControllerDocs {

    @Operation(summary = "게시글 생성")
    @ApiResponse(responseCode = "201", description = "게시글 생성 성공")
    fun create(@Parameter(hidden = true) userId: Long, @RequestBody command: ArticleCreateCommand)
    : ResponseEntity<CommunityApiResponse<ArticleIdReadData>>
}