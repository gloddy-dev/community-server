package gloddy.persistence.comment.adapter

import gloddy.comment.port.out.CommentLikeQueryPort
import gloddy.comment.CommentLike
import gloddy.core.CommentId
import gloddy.core.UserId
import gloddy.persistence.comment.repository.CommentLikeJpaRepository
import gloddy.persistence.util.mapper.toDomain
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