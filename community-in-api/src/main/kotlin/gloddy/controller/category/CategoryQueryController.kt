package gloddy.controller.category

import gloddy.category.port.dto.CategoryReadData
import gloddy.category.port.`in`.CategoryQueryUseCase
import gloddy.response.ApiResponse
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.ok
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/communities")
class CategoryQueryController(
    private val categoryQueryUseCase: CategoryQueryUseCase
) {

    @GetMapping("/categories")
    fun getAll(@RequestHeader("USER_ID") userId: Long): ResponseEntity<ApiResponse<List<CategoryReadData>>> {
        val data = categoryQueryUseCase.getAll()
        return ApiResponseEntityWrapper(data).ok()
    }
}