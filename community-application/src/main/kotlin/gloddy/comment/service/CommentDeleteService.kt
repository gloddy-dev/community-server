package gloddy.comment.service

import gloddy.comment.dto.CommentDeleteRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentDeleteService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort,
) {

    fun delete(request: CommentDeleteRequest) {
        val comment = commentQueryPort.findById(request.commentId)
        commentCommandPort.delete(comment.delete(request.userId))
    }
}