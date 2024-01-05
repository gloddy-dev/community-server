package gloddy.article.dto.command

import gloddy.core.CategoryId
import gloddy.core.UserId

data class ArticleCreateCommand(
    val userId: UserId,
    val categoryId: CategoryId,
    val title: String,
    val content: String,
    val images: List<String>,
)
