package gloddy.fixture

import gloddy.article.Article
import gloddy.article.vo.ArticleImage
import gloddy.category.Category
import gloddy.core.ArticleId
import gloddy.core.UserId
import gloddy.fixture.CategoryFixture.*

enum class ArticleFixture(
    private val userId: UserId?,
    private val category: Category?,
    private val title: String,
    private val content: String,
    private val image: ArticleImage,
) {
    JIHWAN(null, QNA.toPersistDomain(1L), "한국 핫플", "요즘 한국 핫플이 어디에용?", ArticleImage(null));

    fun toDomain(userId: Long): Article =
        Article(
            userId = UserId(userId),
            category = this.category!!,
            title = this.title,
            content = this.content,
            image = this.image
        )

    fun toPersistDomain(userId: Long, id: Long): Article =
        Article(
            userId = UserId(userId),
            category = this.category!!,
            title = this.title,
            content = this.content,
            image = this.image,
            id = ArticleId(id)
        )
}