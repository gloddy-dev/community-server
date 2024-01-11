package gloddy.persistence.comment.adapter

import gloddy.comment.CommentId
import gloddy.comment.port.out.CommentLikeQueryPort
import gloddy.comment.CommentLike
import gloddy.persistence.comment.repository.CommentLikeJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.user.UserId
import org.springframework.stereotype.Component

@Component
class CommentLikeQueryAdapter(
    private val commentLikeJpaRepository: CommentLikeJpaRepository
): CommentLikeQueryPort {

    override fun findByCommentIdAndUserId(commentId: CommentId, userId: UserId): CommentLike? {
        return commentLikeJpaRepository
            .findByCommentIdAndUserId(commentId.value, userId.value)
            ?.toDomain()
    }
}