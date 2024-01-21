package gloddy

import gloddy.article.Article
import gloddy.article.vo.ArticleImage
import gloddy.category.Category
import gloddy.core.ArticleId
import gloddy.core.UserId
import gloddy.CategoryFixture.*

enum class ArticleFixture(
    private val userId: UserId?,
    private val category: Category?,
    private val title: String,
    private val content: String,
    private val image: ArticleImage,
) {
    JIHWAN(null, QNA.toPersistDomain(2L), "한국 핫플", "요즘 한국 핫플이 어디에용?", ArticleImage(null)),
    HAVE_IMAGE(null, LANGUAGE.toPersistDomain(1L), "언어 모임", "언어 모임 하실 분~!", ArticleImage(listOf("image1", "image2")));

    fun toDomain(userId: Long, category: Category? = null): Article =
        Article(
            userId = UserId(userId),
            category = category ?: this.category!!,
            title = this.title,
            content = this.content,
            image = this.image
        )

    fun toPersistDomain(userId: Long, id: Long, category: Category? = null): Article =
        Article(
            userId = UserId(userId),
            category = category ?: this.category!!,
            title = this.title,
            content = this.content,
            image = this.image,
            id = ArticleId(id)
        )
}