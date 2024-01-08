package gloddy.category

import gloddy.core.CategoryId

data class Category(
    var name: String,
    val id: CategoryId? = null,
)
