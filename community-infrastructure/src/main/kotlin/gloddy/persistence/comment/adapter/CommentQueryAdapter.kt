package gloddy.persistence.comment.adapter

import gloddy.article.Article
import gloddy.comment.Comment
import gloddy.comment.CommentId
import gloddy.comment.CommentNotFoundException
import gloddy.comment.dto.readModel.CommentFindByArticleDto
import gloddy.comment.dto.readModel.CommentFindMaxRefDto
import gloddy.comment.port.out.CommentQueryPort
import gloddy.persistence.comment.model.toResponseDto
import gloddy.persistence.comment.repository.CommentJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentQueryAdapter(
    private val commentJpaRepository: CommentJpaRepository
): CommentQueryPort {
    override fun findMaxRefByArticle(article: Article): CommentFindMaxRefDto {
        commentJpaRepository.findOneByArticleOrderByRefDesc(article.toEntity())
            ?.let { return it.toResponseDto() }
            ?: return CommentFindMaxRefDto(0)
    }

    override fun findById(id: CommentId): Comment {
        return commentJpaRepository.findByIdOrNull(id.value)
            ?.toDomain()
            ?: throw CommentNotFoundException()
    }

    override fun findAllByArticle(article: Article, currentUserId: Long): List<CommentFindByArticleDto> {
        return commentJpaRepository.findAllByArticle(article.toEntity(), currentUserId)
            .map { it.toResponseDto() }
    }
}