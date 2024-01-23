package gloddy.comment.event

import java.time.LocalDateTime

data class ParentCommentDeleteEvent(
    val userId: Long,
    val articleId: Long,
    override var commentId: Long = 0L,
    val eventDateTime: LocalDateTime = LocalDateTime.now()
): CommentEvent