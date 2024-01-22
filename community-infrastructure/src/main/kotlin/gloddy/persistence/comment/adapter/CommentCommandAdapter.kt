package gloddy.persistence.comment.adapter

import gloddy.comment.Comment
import gloddy.comment.CommentNotFoundException
import gloddy.comment.port.out.CommentCommandPort
import gloddy.persistence.comment.CommentJpaEntity
import gloddy.persistence.comment.repository.CommentJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CommentCommandAdapter(
    private val commentJpaRepository: CommentJpaRepository
): CommentCommandPort {
    override fun save(comment: Comment): Comment {
        return commentJpaRepository
            .save(comment.toEntity())
            .toDomain()
    }

    override fun delete(id: Long) {
        find(id).changeDeletedToTrue()
    }

    fun find(id: Long): CommentJpaEntity =
        commentJpaRepository.findByIdOrNull(id) ?: throw CommentNotFoundException()
}