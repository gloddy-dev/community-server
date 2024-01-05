package gloddy.persistence.article.adapter

import gloddy.article.Article
import gloddy.article.port.out.ArticleCommandPersistencePort
import gloddy.persistence.article.repository.ArticleJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.stereotype.Component

@Component
class ArticleCommandPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleCommandPersistencePort {

    override fun save(article: Article): Article {
        return articleJpaRepository.save(article.toEntity()).toDomain()
    }
}