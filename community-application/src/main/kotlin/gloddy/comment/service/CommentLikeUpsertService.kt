package gloddy.comment.service

import gloddy.comment.CommentId
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.comment.CommentLike
import gloddy.comment.dto.CommentLikeUpsertRequest
import gloddy.comment.port.out.CommentLikeCommandPort
import gloddy.comment.port.out.CommentLikeQueryPort
import gloddy.user.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentLikeUpsertService(
    private val commentLikeCommandPort: CommentLikeCommandPort,
    private val commentLikeQueryPort: CommentLikeQueryPort,
    private val commentQueryPort: CommentQueryPort,
    private val commentCommandPort: CommentCommandPort,
) {

    @Transactional
    fun upsert(dto: CommentLikeUpsertRequest) {
        commentLikeQueryPort.findByCommentIdAndUserId(
            commentId = dto.commentId,
            userId = dto.userId
        )
        ?. let {
            deleteLike(it)
        }
        ?: run {
            createLike(
                commentId = dto.commentId,
                userId = dto.userId
            )
        }
    }

    fun createLike(commentId: CommentId, userId: UserId) {
        val comment = commentQueryPort.findById(commentId)

        commentLikeCommandPort.save(
            CommentLike(
                userId = userId,
                comment = comment
            )
        )

        commentCommandPort.save(
            comment.copy(
                likeCount = comment.likeCount + 1
            )
        )
    }

    fun deleteLike(commentLike: CommentLike) {
        commentLikeCommandPort.delete(commentLike)
    }
}