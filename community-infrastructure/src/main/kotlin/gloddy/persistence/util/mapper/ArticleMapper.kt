package gloddy.persistence.util.mapper

import gloddy.article.Article
import gloddy.article.ArticleLike
import gloddy.article.vo.ArticleImage
import gloddy.core.ArticleId
import gloddy.core.UserId
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.article.ArticleLikeJpaEntity
import kotlin.io.path.createTempDirectory

fun Article.toEntity() : ArticleJpaEntity =
    ArticleJpaEntity(
        userId = this.userId.value,
        category = this.category.toEntity(),
        title = this.title,
        content = this.content,
        images = this.image.images,
        commentCount = this.commentCount,
        likeCount = this.likeCount,
        createdAt = this.createdAt,
        id = this.id?.value
    )

fun ArticleJpaEntity.toDomain() : Article =
    Article(
        userId = UserId(this.userId),
        category = this.category.toDomain(),
        title = this.title,
        content = this.content,
        image = ArticleImage(this.images),
        commentCount = this.commentCount,
        likeCount = this.likeCount,
        createdAt = this.createdAt!!,
        id = ArticleId(this.id!!)
    )

fun ArticleLike.toEntity() : ArticleLikeJpaEntity =
    ArticleLikeJpaEntity(
        userId = this.userId.value,
        article = this.article.toEntity(),
        id = this.id
    )

fun ArticleLikeJpaEntity.toDomain() : ArticleLike =
    ArticleLike(
        userId = UserId(this.userId),
        article = this.article.toDomain(),
        id = this.id
    )