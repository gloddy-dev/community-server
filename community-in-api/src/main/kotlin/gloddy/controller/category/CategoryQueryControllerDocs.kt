package gloddy.controller.category

import gloddy.category.port.`in`.dto.CategoryGetResponse
import gloddy.response.CommunityApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "카테고리")
interface CategoryQueryControllerDocs {

    @Operation(summary = "카테고리 전체 조회")
    @ApiResponse(responseCode = "200", description = "카테고리 전체 조회 성공")
    fun getAll(@Parameter(hidden = true) userId: Long) : ResponseEntity<CommunityApiResponse<List<CategoryGetResponse>>>
}