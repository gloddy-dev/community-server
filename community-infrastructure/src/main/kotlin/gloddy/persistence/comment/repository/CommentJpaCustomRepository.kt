package gloddy.persistence.comment.repository

import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.comment.model.CommentFindByArticleDto
import gloddy.persistence.comment.model.CommentFindMaxRefDto

interface CommentJpaCustomRepository {
    fun findAllByArticle(article: ArticleJpaEntity, currentUserId: Long): List<CommentFindByArticleDto>
}