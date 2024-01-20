package gloddy.article.port.`in`.dto.command

data class ArticleDetailGetRequest(
    val id: Long,
    val userId: Long
)