package gloddy.comment.event

import java.time.LocalDateTime

data class ChildCommentDeleteEvent(
    val userId: Long,
    val articleId: Long,
    val parentCommentId: Long,
    override var commentId: Long,
    val eventDateTime: LocalDateTime = LocalDateTime.now()
) : CommentEvent