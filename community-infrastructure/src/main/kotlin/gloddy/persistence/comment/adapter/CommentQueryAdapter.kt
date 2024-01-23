package gloddy.persistence.comment.adapter

import gloddy.comment.Comment
import gloddy.comment.CommentNotFoundException
import gloddy.comment.dto.readModel.ChildCommentUnit
import gloddy.comment.dto.readModel.ParentCommentUnit
import gloddy.comment.port.out.CommentQueryPort
import gloddy.core.CommentId
import gloddy.persistence.comment.model.toResponse
import gloddy.persistence.comment.repository.CommentJpaRepository
import gloddy.persistence.util.mapper.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentQueryAdapter(
    private val commentJpaRepository: CommentJpaRepository
): CommentQueryPort {

    override fun findById(id: CommentId): Comment {
        return commentJpaRepository.findByIdOrNull(id.value)
            ?.toDomain()
            ?: throw CommentNotFoundException()
    }

    override fun findParentComments(articleId: Long, userId: Long): List<ParentCommentUnit> {
        return commentJpaRepository.findParentCommentsByArticleId(articleId, userId)
            .map { it.toResponse(userId) }
    }

    override fun findChildComments(parentId: Long, userId: Long): List<ChildCommentUnit> {
        return commentJpaRepository.findChildCommentsByParentId(parentId, userId)
            .map { it.toResponse(userId) }
    }
}