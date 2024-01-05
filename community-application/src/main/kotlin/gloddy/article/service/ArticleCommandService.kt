package gloddy.article.service

import gloddy.article.Article
import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.response.ArticleIdResponse
import gloddy.article.port.`in`.ArticleCommandUseCase
import gloddy.article.port.out.ArticleCommandPersistencePort
import gloddy.category.port.out.CategoryQueryPersistencePort
import gloddy.core.ArticleId
import gloddy.core.UserId
import org.springframework.stereotype.Service

@Service
class ArticleCommandService(
    private val categoryQueryPersistencePort: CategoryQueryPersistencePort,
    private val articleCommandPersistencePort: ArticleCommandPersistencePort,
) : ArticleCommandUseCase {

    override fun create(command: ArticleCreateCommand) : ArticleIdResponse {

        val category = categoryQueryPersistencePort.findById(command.categoryId)

        val article = Article(
            userId = command.userId,
            category = category,
            title = command.title,
            content = command.content,
            images = command.images,
        ).let { articleCommandPersistencePort.save(it) }
        return ArticleIdResponse(articleId = article.id!!)
    }
}