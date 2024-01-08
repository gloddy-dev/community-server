package gloddy.persistence.article.adapter

import gloddy.article.ArticleLike
import gloddy.article.port.out.ArticleLikeCommandPersistencePort
import gloddy.persistence.article.repository.ArticleLikeJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ArticleLikeCommandPersistenceAdapter(
    private val articleLikeJpaRepository: ArticleLikeJpaRepository
) : ArticleLikeCommandPersistencePort {

    override fun save(articleLike: ArticleLike): ArticleLike {
        return articleLikeJpaRepository.save(articleLike.toEntity()).toDomain()
    }

    override fun delete(articleLike: ArticleLike) {
        articleLikeJpaRepository.delete(articleLike.toEntity())
    }
}