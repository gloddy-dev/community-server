package gloddy.article.dto.command

data class ArticleCreateCommand(
    val categoryId: Long,
    val title: String,
    val content: String,
    val images: List<String>?,
)
