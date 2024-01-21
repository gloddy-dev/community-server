package gloddy.controller.article

import gloddy.article.port.`in`.dto.command.ArticleCreateRequest
import gloddy.article.port.`in`.dto.read.ArticleCreateResponse
import gloddy.article.port.`in`.ArticleCommandUseCase
import gloddy.response.CommunityApiResponse
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.created
import gloddy.response.noContent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/communities")
class ArticleCommandController(
    private val articleCommandUseCase: ArticleCommandUseCase,
) : ArticleCommandControllerDocs {


    @PostMapping("/articles/create")
    override fun create(
        @RequestHeader("USER_ID") userId: Long,
        @RequestBody command: ArticleCreateRequest,
    ): ResponseEntity<CommunityApiResponse<ArticleCreateResponse>> {
        val data = articleCommandUseCase.create(userId, command)
        return ApiResponseEntityWrapper(data).created()
    }

    @PostMapping("/articles/{articleId}/delete")
    override fun delete(@RequestHeader("USER_ID") userId: Long, @PathVariable("articleId") articleId: Long)
            : ResponseEntity<CommunityApiResponse<Nothing>> {
        articleCommandUseCase.delete(userId, articleId)
        return ApiResponseEntityWrapper<Nothing>().noContent()
    }

    @PostMapping("/articles/{articleId}/like")
    override fun like(@RequestHeader("USER_ID") userId: Long, @PathVariable("articleId") articleId: Long)
            : ResponseEntity<CommunityApiResponse<Nothing>> {
        articleCommandUseCase.upsertLike(userId, articleId)
        return ApiResponseEntityWrapper<Nothing>().noContent()
    }
}