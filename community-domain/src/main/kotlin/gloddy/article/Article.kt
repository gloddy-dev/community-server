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
    val id: ArticleId? = null,
) {
    var commentCount: Int = 0
    var likeCount: Int = 0

    constructor(
        userId: UserId,
        category: Category,
        title: String,
        content: String,
        imageList: List<String>?,
        id: ArticleId? = null
    ) : this(
        userId = userId,
        category = category,
        title = title,
        content = content,
        image = ArticleImage(imageList),
        id = id
    )
}