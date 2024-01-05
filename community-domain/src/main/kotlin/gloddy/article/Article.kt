package gloddy.article

import gloddy.article.vo.ArticleImage
import gloddy.category.Category
import gloddy.core.ArticleId
import gloddy.core.UserId

data class Article(
    val userId: UserId,
    var category: Category,
    var title: String,
    var content: String,
    var image: ArticleImage,
    var commentCount: Int = 0,
    var likeCount: Int = 0,
    val id: ArticleId? = null,
) {
    constructor(
        userId: UserId,
        category: Category,
        title: String,
        content: String,
        images: List<String>?,
        id: ArticleId? = null
    ) : this(
        userId = userId,
        category = category,
        title = title,
        content = content,
        image = ArticleImage(images),
        id = id
    )
}