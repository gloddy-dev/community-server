package gloddy.article.port.`in`.dto.command

data class ArticleUpsertCommentRequest(
    val articleId: Long,
    val status: CommentStatus
)

enum class CommentStatus {
    CREATE, DELETE
}