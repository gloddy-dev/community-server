package gloddy.article.service

import gloddy.article.Article
import gloddy.article.ArticleLike
import gloddy.article.dto.command.ArticleCreateCommand
import gloddy.article.dto.read.ArticleIdReadData
import gloddy.article.port.`in`.ArticleCommandUseCase
import gloddy.article.port.out.ArticleCommandPersistencePort
import gloddy.article.port.out.ArticleLikeCommandPersistencePort
import gloddy.article.port.out.ArticleLikeQueryPersistencePort
import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.category.port.out.CategoryQueryPersistencePort
import gloddy.core.CategoryId
import gloddy.core.UserId
import org.springframework.stereotype.Service

@Service
class ArticleCommandService(
    private val categoryQueryPersistencePort: CategoryQueryPersistencePort,
    private val articleQueryPersistencePort: ArticleQueryPersistencePort,
    private val articleCommandPersistencePort: ArticleCommandPersistencePort,
    private val articleLikeCommandPersistencePort: ArticleLikeCommandPersistencePort,
    private val articleLikeQueryPersistencePort: ArticleLikeQueryPersistencePort
) : ArticleCommandUseCase {

    override fun create(userId: Long, command: ArticleCreateCommand) : ArticleIdReadData {

        val category = categoryQueryPersistencePort.findById(CategoryId(command.categoryId))

        val article = Article(
            userId = UserId(userId),
            category = category,
            title = command.title,
            content = command.content,
            images = command.images,
        ).let { articleCommandPersistencePort.save(it) }
        return ArticleIdReadData(articleId = article.id!!.value)
    }

    override fun delete(userId: Long, articleId: Long) {
        val article = articleQueryPersistencePort.findById(articleId)
        article.validateAuthorization(userId)
        articleCommandPersistencePort.delete(article.id!!.value)
    }

    override fun like(userId: Long, articleId: Long) {

        val article = articleQueryPersistencePort.findById(articleId)

        articleLikeQueryPersistencePort.findByUserIdAndArticleOrNull(userId, article)
            ?.run { articleLikeCommandPersistencePort.delete(this) }
            ?: articleLikeCommandPersistencePort.save(
                ArticleLike(
                    userId = UserId(userId),
                    article = article
                )
            )
    }
}