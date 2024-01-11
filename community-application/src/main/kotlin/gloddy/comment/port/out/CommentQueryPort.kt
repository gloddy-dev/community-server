package gloddy.comment.port.out

import gloddy.article.Article
import gloddy.comment.Comment
import gloddy.comment.CommentId
import gloddy.comment.dto.readModel.CommentFindByArticleDto
import gloddy.comment.dto.readModel.CommentFindMaxRefDto


interface CommentQueryPort {
    fun findMaxRefByArticle(article: Article): CommentFindMaxRefDto
    fun findById(id: CommentId): Comment
    fun findAllByArticle(article: Article, currentUserId: Long): List<CommentFindByArticleDto>
}