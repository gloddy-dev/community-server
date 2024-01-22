package gloddy.controller.comment

import gloddy.comment.ArticleId
import gloddy.comment.CommentId
import gloddy.comment.dto.*
import gloddy.comment.service.CommentCreateService
import gloddy.comment.service.CommentDeleteService
import gloddy.comment.service.CommentLikeUpsertService
import gloddy.controller.comment.model.CommentCreateRequest
import gloddy.response.*
import gloddy.user.UserId
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/communities/articles")
class CommentController(
    private val commentCreateService: CommentCreateService,
    private val commentDeleteService: CommentDeleteService,
    private val commentLikeUpsertService: CommentLikeUpsertService,
    private val commentLikeDeleteService: CommentDeleteService
): CommentControllerDocs {

    @PostMapping("/{articleId}/comments")
    override fun createParent(
        @RequestHeader("USER_ID") userId: Long,
        @PathVariable("articleId") articleId: Long,
        @RequestBody request: CommentCreateRequest
    ): ResponseEntity<CommunityApiResponse<CommentCreateResponse>> {
        val data = commentCreateService.createParent(
            ParentCommentCreateRequest(
                userId = UserId(userId),
                articleId = ArticleId(articleId),
                content = request.content
            )
        )

        return ApiResponseEntityWrapper(data).created()
    }

    @PostMapping("/{articleId}/comments/{commentId}/child")
    override fun createChild(
        @RequestHeader("USER_ID") userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long,
        @RequestBody request: CommentCreateRequest
    ): ResponseEntity<CommunityApiResponse<CommentCreateResponse>> {
        val data = commentCreateService.createChild(
            ChildCommentCreateRequest(
                userId = UserId(userId),
                articleId = ArticleId(articleId),
                parentCommentId = CommentId(commentId),
                content = request.content
            )
        )

        return ApiResponseEntityWrapper(data).created()
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    override fun delete(
        @RequestHeader("USER_ID") userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long,
    ): ResponseEntity<CommunityApiResponse<Nothing>> {
        commentDeleteService.delete(
            CommentDeleteRequest(
                commentId = CommentId(commentId),
                userId = UserId(userId),
                articleId = ArticleId(articleId)
            )
        )

        return ApiResponseEntityWrapper<Nothing>().noContent()
    }

    @PostMapping("/{articleId}/comments/{commentId}/like")
    override fun like(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long,
    ): ResponseEntity<CommunityApiResponse<Nothing>> {
        commentLikeUpsertService.upsert(
            CommentLikeUpsertRequest(
                userId = UserId(userId),
                commentId = CommentId(commentId)
            )
        )
        return ApiResponseEntityWrapper<Nothing>().noContent()
    }
}