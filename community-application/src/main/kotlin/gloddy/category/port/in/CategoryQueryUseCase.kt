package gloddy.category.port.`in`

import gloddy.category.port.`in`.dto.CategoryGetResponse

interface CategoryQueryUseCase {
    fun getAll(): List<CategoryGetResponse>
}