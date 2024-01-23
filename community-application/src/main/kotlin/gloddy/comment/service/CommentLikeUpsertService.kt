package gloddy.comment.service

import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.comment.CommentLike
import gloddy.comment.dto.CommentLikeUpsertRequest
import gloddy.comment.port.out.CommentLikeCommandPort
import gloddy.comment.port.out.CommentLikeQueryPort
import gloddy.core.CommentId
import gloddy.core.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentLikeUpsertService(
    private val commentLikeQueryPort: CommentLikeQueryPort,
    private val commentQueryPort: CommentQueryPort,
    private val commentCommandPort: CommentCommandPort,
) {

    @Transactional
    fun upsert(dto: CommentLikeUpsertRequest) {
        commentLikeQueryPort.findByCommentIdAndUserIdOrNull(
            commentId = dto.commentId,
            userId = dto.userId
        )
            ?.run {
                unLike(dto.commentId, this)
            }
            ?: run {
                like(dto.commentId, dto.userId)
            }
    }

    private fun like(commentId: CommentId, userId: UserId) {
        val comment = commentQueryPort.findById(commentId)
        commentCommandPort.upsertLike(
            commentLike = CommentLike(
                userId = userId,
                comment = comment
            ),
            comment = comment.like()
        )
    }

    private fun unLike(commentId: CommentId, commentLike: CommentLike) {
        val comment = commentQueryPort.findById(commentId)
        commentCommandPort.upsertLike(
            commentLike = commentLike,
            comment = comment.unLike()
        )
    }
}