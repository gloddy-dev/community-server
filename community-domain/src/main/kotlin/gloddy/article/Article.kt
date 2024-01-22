package gloddy.article

import gloddy.article.vo.ArticleImage
import gloddy.category.Category
import gloddy.core.ArticleId
import gloddy.core.UserId
import java.time.LocalDateTime
import java.time.LocalDateTime.*

data class Article(
    val userId: UserId,
    val category: Category,
    val title: String,
    val content: String,
    val image: ArticleImage,
    val commentCount: Int = 0,
    val likeCount: Int = 0,
    val createdAt: LocalDateTime = now(),
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

    fun validateAuthorization(userId: Long) {
        if (this.userId.value != userId) {
            throw ArticleNoAuthorizationException()
        }
    }

    fun like(): Article =
        this.copy(
            likeCount = this.likeCount + 1
        )

    fun unlike(): Article =
        this.copy(
            likeCount = this.likeCount - 1
        )
}