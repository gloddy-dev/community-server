package gloddy.article.port.`in`

import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.response.ArticleIdResponse
import gloddy.core.UserId

interface ArticleCommandUseCase {
    fun create(command: ArticleCreateCommand): ArticleIdResponse
    fun delete(userId: Long, articleId: Long)
    fun like(userId: Long, articleId: Long)
}