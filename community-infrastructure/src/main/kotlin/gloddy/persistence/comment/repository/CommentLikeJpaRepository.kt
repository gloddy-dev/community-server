package gloddy.persistence.comment.repository

import gloddy.persistence.comment.CommentLikeJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeJpaRepository : JpaRepository<CommentLikeJpaEntity, Long> {
    fun findByCommentIdAndUserId(commentId: Long, userId: Long): CommentLikeJpaEntity?
}