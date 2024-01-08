package gloddy.article.port.out

import gloddy.article.ArticleLike

interface ArticleLikeCommandPersistencePort {
    fun save(articleLike: ArticleLike): ArticleLike
    fun delete(articleLike: ArticleLike)
}