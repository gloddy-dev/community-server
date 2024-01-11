package gloddy.comment.service

import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.comment.Comment
import gloddy.comment.dto.CommentGetRequest
import gloddy.comment.dto.readModel.CommentFindByArticleDto
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentGetService(
    private val articleQueryPersistencePort: ArticleQueryPersistencePort,
    private val commentQueryPort: CommentQueryPort
) {
    fun findByArticle(dto: CommentGetRequest): List<CommentFindByArticleDto> {
        val article = articleQueryPersistencePort.findById(dto.articleId.value)
        return commentQueryPort.findAllByArticle(article, dto.userId.value)
    }
}