package gloddy.comment.dto.readModel

import gloddy.article.Article
import java.time.LocalDateTime

data class CommentFindByArticleDto(
    val id: Long,
    val userId: Long,
    val article: Article,
    val likeCount: Long,
    val content: String,
    val depth: Int,
    val ref: Int,
    val createdAt: LocalDateTime,
    val likedUserId: Long?
)