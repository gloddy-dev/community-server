package gloddy.category.port.out

import gloddy.category.Category
import gloddy.core.CategoryId

interface CategoryQueryPersistencePort {
    fun findById(id: CategoryId): Category
    fun findAll(): List<Category>
}