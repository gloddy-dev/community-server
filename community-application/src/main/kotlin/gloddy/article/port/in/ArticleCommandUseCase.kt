package gloddy.article.port.`in`

import gloddy.article.port.`in`.dto.command.ArticleCreateRequest
import gloddy.article.port.`in`.dto.read.ArticleCreateResponse

interface ArticleCommandUseCase {
    fun create(userId: Long, command: ArticleCreateRequest): ArticleCreateResponse
    fun delete(userId: Long, articleId: Long)
    fun upsertLike(userId: Long, articleId: Long)
}