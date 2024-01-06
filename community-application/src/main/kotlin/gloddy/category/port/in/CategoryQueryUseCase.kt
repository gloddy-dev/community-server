package gloddy.category.port.`in`

import gloddy.category.port.dto.CategoryReadData

interface CategoryQueryUseCase {
    fun getAll(): List<CategoryReadData>
}