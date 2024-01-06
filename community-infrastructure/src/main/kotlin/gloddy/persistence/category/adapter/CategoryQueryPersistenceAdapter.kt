package gloddy.persistence.category.adapter

import gloddy.category.Category
import gloddy.category.exception.CategoryNotFoundException
import gloddy.category.port.out.CategoryQueryPersistencePort
import gloddy.core.CategoryId
import gloddy.persistence.category.repository.CategoryJpaRepository
import gloddy.persistence.util.mapper.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CategoryQueryPersistenceAdapter(
    private val categoryJpaRepository: CategoryJpaRepository,
) : CategoryQueryPersistencePort {

    override fun findById(id: CategoryId): Category {
        return (categoryJpaRepository.findByIdOrNull(id.value) ?: throw CategoryNotFoundException()).toDomain()
    }

    override fun findAll(): List<Category> {
        return categoryJpaRepository.findAll()
            .map { it.toDomain() }
    }
}