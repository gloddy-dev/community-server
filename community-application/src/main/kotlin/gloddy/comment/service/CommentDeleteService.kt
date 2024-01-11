package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.CommentNotAuthorizationException
import gloddy.comment.dto.CommentDeleteRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.user.User
import gloddy.user.UserId
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Service

@Service
class CommentDeleteService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort,
) {

    fun delete(dto: CommentDeleteRequest) {
        val comment = commentQueryPort.findById(dto.commentId)

        isWriter(comment, dto.userId)
        commentCommandPort.delete(comment)
    }

    fun isWriter(comment: Comment, userId: UserId) {
        if (comment.isWriter(userId).not()) {
            throw CommentNotAuthorizationException()
        }
    }
}