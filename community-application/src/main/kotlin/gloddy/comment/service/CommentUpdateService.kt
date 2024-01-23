package gloddy.comment.service

import gloddy.article.port.`in`.dto.command.CommentStatus
import gloddy.comment.dto.CommentChildUpsertRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Component

@Component
class CommentUpdateService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort,
) {

    fun upsertChildCount(request: CommentChildUpsertRequest) {
        val comment = commentQueryPort.findById(request.commentId)
        when(request.status) {
            CommentStatus.CREATE -> commentCommandPort.save(comment.plusChild())
            CommentStatus.DELETE -> commentCommandPort.save(comment.upPlusChild())
        }
    }
}