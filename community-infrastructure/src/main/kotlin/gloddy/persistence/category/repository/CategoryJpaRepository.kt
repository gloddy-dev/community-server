package gloddy.persistence.category.repository

import gloddy.persistence.category.CategoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<CategoryJpaEntity, Long> {
}