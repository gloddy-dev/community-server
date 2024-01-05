package gloddy.article.port.out

import gloddy.article.Article

interface ArticleQueryPersistencePort {
    fun findById(id: Long): Article
}