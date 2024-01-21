package gloddy.article.port.out

import gloddy.article.Article
import gloddy.article.ArticleLike

interface ArticleCommandPersistencePort {
    fun save(article: Article) : Article
    fun delete(id: Long)
    fun upsertLike(articleLike: ArticleLike, article: Article)
}