package gloddy.persistence.comment.adapter

import gloddy.comment.port.out.CommentLikeCommandPort
import gloddy.comment.CommentLike
import gloddy.persistence.comment.repository.CommentLikeJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.stereotype.Component

@Component
class CommentLikeCommandAdapter(
    private val commentLikeJpaRepository: CommentLikeJpaRepository
): CommentLikeCommandPort {
    override fun save(commentLike: CommentLike): CommentLike {
        return commentLikeJpaRepository
            .save(commentLike.toEntity())
            .toDomain()
    }

    override fun delete(commentLike: CommentLike) {
        commentLikeJpaRepository.delete(commentLike.toEntity())
    }
}
