package gloddy.article

import gloddy.article.vo.ArticleImage
import gloddy.article.vo.Like
import gloddy.category.Category
import gloddy.core.ArticleId
import gloddy.core.UserId

data class Article(
    val id: ArticleId?,
    val userId: UserId,
    val category: Category,
    val title: String,
    val content: String,
    val image: ArticleImage,
    val like: Like
)