package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.CommentNotAuthorizationException
import gloddy.comment.dto.CommentDeleteRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentDeleteService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort
    private val userQueryPort: UserQueryPort
) {

    fun delete(dto: CommentDeleteRequest) {
        val user = userQueryPort.findById(dto.userId)
        val comment = commentQueryPort.findById(dto.commentId)

        isWriter(comment, user)
        commentCommandPort.delete(comment)
    }

    fun isWriter(comment: Comment, user: User) {
        if (comment.isWriter(user).not()) {
            throw CommentNotAuthorizationException()
        }
    }
}