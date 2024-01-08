package gloddy.controller.article

import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.read.ArticleIdReadData
import gloddy.article.port.`in`.ArticleCommandUseCase
import gloddy.response.CommunityApiResponse
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.created
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/communities")
class ArticleCommandController(
    private val articleCommandUseCase: ArticleCommandUseCase,
) : ArticleCommandControllerDocs {


    @PostMapping("/articles/create")
    override fun create(
        @RequestHeader("USER_ID") userId: Long,
        @RequestBody command: ArticleCreateCommand,
    ): ResponseEntity<CommunityApiResponse<ArticleIdReadData>> {
        val data = articleCommandUseCase.create(userId, command)
        return ApiResponseEntityWrapper(data).created()
    }
}