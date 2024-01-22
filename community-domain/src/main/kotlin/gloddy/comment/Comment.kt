package gloddy.comment

import gloddy.article.Article
import gloddy.core.CommentId
import gloddy.core.UserId
import java.time.LocalDateTime

data class Comment(
    val id: CommentId? = CommentId(0L),
    val userId: UserId,
    val article: Article,
    val content: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val parentId: CommentId,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    init {
        require(likeCount >= 0) { "likeCount must be greater than 0 or equal" }
    }

    companion object {
        fun parent(
            userId: UserId,
            article: Article,
            content: String
        ): Comment =
            Comment(
                userId = userId,
                article = article,
                content = content,
                parentId = CommentId(0L)
            )

        fun child(
            userId: UserId,
            article: Article,
            content: String,
            parentCommentId: CommentId
        ): Comment =
            Comment(
                userId = userId,
                article = article,
                content = content,
                parentId = parentCommentId
            )
    }

    fun isWriter(currentUserId: UserId): Boolean {
        return this.userId == currentUserId
    }
}