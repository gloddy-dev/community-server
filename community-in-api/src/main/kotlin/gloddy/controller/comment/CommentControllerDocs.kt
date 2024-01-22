package gloddy.controller.comment

import gloddy.comment.dto.CommentCreateResponse
import gloddy.controller.comment.model.CommentCreateRequest
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "게시글")
interface CommentControllerDocs {

    @Operation(summary = "부모 댓글 생성")
    @ApiResponse(responseCode = "201", description = "부모 댓글 생성 성공")
    fun createParent(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<CommunityApiResponse<CommentCreateResponse>>

    @Operation(summary = "자식 댓글 생성")
    @ApiResponse(responseCode = "201", description = "자식 댓글 생성 성공")
    fun createChild(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<CommunityApiResponse<CommentCreateResponse>>

    @Operation(summary = "댓글 삭제")
    @ApiResponse(responseCode = "204", description = "댓글 삭제 성공")
    fun delete(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long
    ): ResponseEntity<CommunityApiResponse<Nothing>>

    @Operation(summary = "댓글 좋아요 및 좋아요 취소")
    @ApiResponse(responseCode = "204", description = "게시글 좋아요 및 좋아요 취소 성공")
    fun like(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long,
        @PathVariable("commentId") commentId: Long,
    ): ResponseEntity<CommunityApiResponse<Nothing>>
}