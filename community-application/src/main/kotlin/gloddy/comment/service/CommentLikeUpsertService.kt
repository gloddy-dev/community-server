package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.like.CommentLike
import gloddy.comment.dto.CommentLikeCreateRequest
import gloddy.comment.port.out.CommentLikeCommandPort
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentLikeCreateService(
    private val commentLikeCommandPort: CommentLikeCommandPort,
    private val commentQueryPort: CommentQueryPort,
    private val commentCommandPort: CommentCommandPort,
) {

    @Transactional
    fun create(dto: CommentLikeCreateRequest) {
        val comment = commentQueryPort.findById(dto.commentId)

        CommentLike(
            userId = dto.userId,
            comment = comment
        )
        .also { increaseLikeCount(comment) }
        .run { commentLikeCommandPort.create(this) }
    }

    fun increaseLikeCount(comment: Comment) {
        val newLikeCount = comment.likeCount + 1
        val newComment = comment.copy(likeCount = newLikeCount)

        commentCommandPort.save(newComment)
    }
}