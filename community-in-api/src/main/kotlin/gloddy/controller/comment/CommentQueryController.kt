package gloddy.controller.comment

import gloddy.comment.dto.CommentGetRequest
import gloddy.comment.dto.readModel.FindParentCommentsByArticleIdResponse
import gloddy.comment.service.CommentQueryService
import gloddy.core.ArticleId
import gloddy.core.UserId
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.CommunityApiResponse
import gloddy.response.ok
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/communities")
class CommentQueryController(
    private val commentQueryService: CommentQueryService
): CommentQueryControllerDocs {

    @GetMapping("/articles/{articleId}/comments")
    override fun getParentComments(
        @RequestHeader("USER_ID") userId: Long,
        @PathVariable("articleId") articleId: Long,
    ): ResponseEntity<CommunityApiResponse<FindParentCommentsByArticleIdResponse>> {
        return commentQueryService.getParentComments(
            CommentGetRequest(
                userId = UserId(userId),
                articleId = ArticleId(articleId)
            )
        ).let { ApiResponseEntityWrapper(it).ok() }
    }
}