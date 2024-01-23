package gloddy.comment

import gloddy.article.Article
import gloddy.comment.event.*
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
    val events: ArrayList<out CommentEvent> = arrayListOf()
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
                parentId = CommentId(0L),
                events = arrayListOf(ParentCommentCreateEvent(
                    userId = userId.value,
                    articleId = article.id!!.value,
                ))
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
                parentId = parentCommentId,
                events = arrayListOf(
                    ChildCommentCreateEvent(
                        userId = userId.value,
                        articleId = article.id!!.value,
                        parentCommentId = parentCommentId.value,
                    )
                )
            )
    }

    fun delete(userId: UserId): Comment {
        isWriter(userId)
        return when (isParent()) {
            true -> this.copy(
                events = arrayListOf(ParentCommentDeleteEvent(
                    userId = userId.value,
                    articleId = article.id!!.value,
                    commentId = this.id!!.value
                ))
            )
            false -> this.copy(
                events = arrayListOf(
                    ChildCommentDeleteEvent(
                        userId = userId.value,
                        articleId = article.id!!.value,
                        parentCommentId = this.parentId.value,
                        commentId = this.id!!.value,
                    )
                )
            )
        }
    }

    fun plusChild(): Comment =
        this.copy(
            commentCount = commentCount + 1
        )

    fun upPlusChild(): Comment =
        this.copy(
            commentCount = commentCount - 1
        )

    fun like(): Comment =
        this.copy(
            likeCount = likeCount + 1
        )

    fun unLike(): Comment =
        this.copy(
            likeCount = likeCount -1
        )

    private fun isWriter(currentUserId: UserId) {
        if (currentUserId != this.userId) {
            throw CommentNotAuthorizationException()
        }
    }

    private fun isParent(): Boolean =
        this.parentId.value == 0L
}