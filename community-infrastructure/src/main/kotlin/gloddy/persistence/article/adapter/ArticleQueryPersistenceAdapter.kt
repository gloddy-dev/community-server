package gloddy.persistence.article.adapter

import gloddy.article.Article
import gloddy.article.exception.ArticleNotFoundException
import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.persistence.article.repository.ArticleJpaRepository
import gloddy.persistence.util.mapper.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ArticleQueryPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository,
) : ArticleQueryPersistencePort {

    override fun findById(id: Long): Article {
        return (articleJpaRepository.findByIdOrNull(id) ?: throw ArticleNotFoundException()).toDomain()
    }
}