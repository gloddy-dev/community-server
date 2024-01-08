package gloddy.persistence.article.adapter

import gloddy.article.Article
import gloddy.article.ArticleLike
import gloddy.article.port.out.ArticleLikeQueryPersistencePort
import gloddy.persistence.article.repository.ArticleLikeJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.stereotype.Component

@Component
class ArticleLikeQueryPersistenceAdapter(
    private val articleLikeJpaRepository: ArticleLikeJpaRepository,
) : ArticleLikeQueryPersistencePort {

    override fun findByUserIdAndArticleOrNull(userId: Long, article: Article): ArticleLike? {
        return articleLikeJpaRepository.findByUserIdAndArticle(userId, article.toEntity())?.toDomain()
    }
}