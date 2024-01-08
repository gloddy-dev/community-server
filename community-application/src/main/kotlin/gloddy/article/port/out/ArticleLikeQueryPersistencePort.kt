package gloddy.article.port.out

import gloddy.article.Article
import gloddy.article.ArticleLike

interface ArticleLikeQueryPersistencePort {
    fun findByUserIdAndArticleOrNull(userId: Long, article: Article): ArticleLike?
}