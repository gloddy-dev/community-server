package gloddy.comment

import gloddy.user.UserId
import java.time.LocalDateTime

@JvmInline
value class ArticleId(val value: Long)

@JvmInline
value class CommentId(val value: Long)

data class Comment(
    val id: CommentId? = CommentId(0L),
    val userId: UserId,
    val articleId: ArticleId,
    val likeCount: Long,
    val content: String,
    val depth: Int,
    val ref: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun init(userId: UserId, articleId: ArticleId, content: String, depth: Int, ref: Int): Comment {
            return Comment(
                userId = userId,
                articleId = articleId,
                likeCount = 0L,
                content = content,
                depth = depth,
                ref = ref,
                createdAt = LocalDateTime.now()
            )
        }
    }
}