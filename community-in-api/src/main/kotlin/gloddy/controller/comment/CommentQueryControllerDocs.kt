package gloddy.controller.comment

import gloddy.comment.dto.readModel.FindParentCommentsByArticleIdResponse
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@Tag(name = "댓글")
interface CommentQueryControllerDocs {

    @Operation(summary = "게시글의 부모(최상위) 댓글 조회")
    @ApiResponse(responseCode = "200", description = "게시글의 부모 댓글 조회 성공")
    fun getParentComments(
        @Parameter(hidden = true) userId: Long,
        @PathVariable("articleId") articleId: Long
    ): ResponseEntity<CommunityApiResponse<FindParentCommentsByArticleIdResponse>>
}