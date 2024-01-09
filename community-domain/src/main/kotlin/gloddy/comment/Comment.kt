package gloddy.comment

import java.time.LocalDateTime

@JvmInline
value class ArticleId(val value: Long)

@JvmInline
value class CommentId(val value: Long)

data class Comment(
    val id: CommentId? = CommentId(0L),
    val user: User,
    val article: Article,
    val likeCount: Long,
    val content: String,
    val depth: Int,
    val ref: Int,
    val createdAt: LocalDateTime
) {
    init {
        require(likeCount >= 0) { "likeCount must be greater than 0 or equal" }
        require(depth >= 0) { "depth must be greater than 0 or equal" }
    }

    constructor(
        user: User,
        article: Article,
        content: String,
        depth: Int,
        ref: Int
    ): this(
        user = user,
        article = article,
        likeCount = 0L,
        content = content,
        depth = depth,
        ref = ref,
        createdAt = LocalDateTime.now()
    )

    fun isWriter(user: User): Boolean {
        return this.user == user
    }
}