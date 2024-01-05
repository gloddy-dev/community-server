package gloddy

import gloddy.category.Category
import gloddy.core.CategoryId

enum class CategoryFixture(
    private val names: String,
) {
    K_POP("K-POP"),
    QNA("Q&A"),
    LANGUAGE("Language");

    fun toDomain(): Category =
        Category(
            name = this.names
        )

    fun toPersistDomain(id: Long): Category =
        Category(
            name = names,
            id = CategoryId(id)
        )
}