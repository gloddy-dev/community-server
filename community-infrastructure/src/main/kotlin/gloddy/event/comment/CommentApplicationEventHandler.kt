package gloddy.event.comment

import gloddy.article.port.`in`.dto.command.CommentStatus
import gloddy.comment.dto.CommentChildUpsertRequest
import gloddy.comment.event.ChildCommentDeleteEvent
import gloddy.comment.service.CommentUpdateService
import gloddy.core.CommentId
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CommentApplicationEventHandler(
    private val commentUpdateService: CommentUpdateService
) {

    @EventListener
    fun handleChildCommentDeleteEvent(event: ChildCommentDeleteEvent) {
        commentUpdateService.upsertChildCount(
            CommentChildUpsertRequest(
                commentId = CommentId(event.commentId),
                status = CommentStatus.DELETE
            )
        )
    }
}