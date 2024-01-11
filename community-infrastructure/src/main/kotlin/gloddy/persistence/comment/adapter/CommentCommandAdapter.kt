package gloddy.persistence.comment.adapter

import gloddy.comment.Comment
import gloddy.comment.port.out.CommentCommandPort
import gloddy.persistence.comment.repository.CommentJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.stereotype.Component

@Component
class CommentCommandAdapter(
    private val commentJpaRepository: CommentJpaRepository
): CommentCommandPort {
    override fun save(comment: Comment): Comment {
        return commentJpaRepository
            .save(comment.toEntity())
            .toDomain()
    }

    override fun delete(comment: Comment) {
        commentJpaRepository.delete(comment.toEntity())
    }
}