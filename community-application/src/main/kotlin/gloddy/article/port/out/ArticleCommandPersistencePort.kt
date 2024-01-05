package gloddy.article.port.out

import gloddy.article.Article

interface ArticleCommandPersistencePort {
    fun save(article: Article) : Article
}