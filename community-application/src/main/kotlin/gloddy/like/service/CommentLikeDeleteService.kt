package gloddy.like.service

import gloddy.comment.Comment
import gloddy.comment.port.out.CommentCommandPort
import gloddy.like.dto.CommentLikeDeleteRequest
import gloddy.like.port.out.CommentLikeCommandPort
import gloddy.like.port.out.CommentLikeQueryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentLikeDeleteService(
    private val commentLikeCommandPort: CommentLikeCommandPort,
    private val commentLikeQueryPort: CommentLikeQueryPort,
    private val commentCommandPort: CommentCommandPort
) {

    @Transactional
    fun delete(dto: CommentLikeDeleteRequest) {
        with(dto) {
            commentLikeQueryPort.findByCommentIdAndUserId(commentId, userId)
        }
        .also { describeLikeCount(it.comment) }
        .run { commentLikeCommandPort.delete(this) }
    }

    fun describeLikeCount(comment: Comment) {
        val newLikeCount = comment.likeCount - 1
        val newComment = comment.copy(likeCount = newLikeCount)

        commentCommandPort.save(newComment)
    }
}