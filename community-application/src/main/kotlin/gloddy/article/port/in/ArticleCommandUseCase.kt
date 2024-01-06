package gloddy.article.port.`in`

import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.read.ArticleIdReadData

interface ArticleCommandUseCase {
    fun create(userId: Long, command: ArticleCreateCommand): ArticleIdReadData
    fun delete(userId: Long, articleId: Long)
    fun like(userId: Long, articleId: Long)
}