package gloddy.article.port.`in`.dto.command

data class ArticleCreateRequest(
    val categoryId: Long,
    val title: String,
    val content: String,
    val images: List<String>?,
)
