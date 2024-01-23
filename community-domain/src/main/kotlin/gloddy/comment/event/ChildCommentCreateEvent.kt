package gloddy.comment.event

import java.time.LocalDateTime

data class ChildCommentCreateEvent(
    val userId: Long,
    val articleId: Long,
    val parentCommentId: Long,
    override var commentId: Long = 0L,
    val eventDateTime: LocalDateTime = LocalDateTime.now()
) : CommentEvent
