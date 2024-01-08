package gloddy.persistence.util.mapper

import gloddy.category.Category
import gloddy.core.CategoryId
import gloddy.persistence.category.CategoryJpaEntity

fun Category.toEntity() : CategoryJpaEntity =
    CategoryJpaEntity(
        name = this.name,
        id = this.id?.value
    )

fun CategoryJpaEntity.toDomain() : Category =
    Category(
        name = this.name,
        id = CategoryId(this.id!!)
    )