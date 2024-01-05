package gloddy.persistence.article.adapter

import gloddy.article.Article
import gloddy.article.exception.ArticleNotFoundException
import gloddy.article.port.out.ArticleCommandPersistencePort
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.article.repository.ArticleJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ArticleCommandPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleCommandPersistencePort {

    override fun save(article: Article): Article {
        return articleJpaRepository.save(article.toEntity()).toDomain()
    }

    override fun delete(id: Long) {
        val articleJpaEntity = find(id)
        articleJpaEntity.changeDeletedToTrue()
    }

    fun find(id: Long): ArticleJpaEntity {
        return articleJpaRepository.findByIdOrNull(id) ?: throw ArticleNotFoundException()
    }
}