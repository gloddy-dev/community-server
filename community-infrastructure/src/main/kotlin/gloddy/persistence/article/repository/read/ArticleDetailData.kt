package gloddy.persistence.article.repository.read

import com.querydsl.core.annotations.QueryProjection
import gloddy.article.port.`in`.dto.read.ArticleDetailUnit
import gloddy.category.port.`in`.dto.CategoryGetResponse
import gloddy.core.util.toResponse
import java.time.LocalDateTime

data class ArticleDetailData @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val categoryId: Long,
    val categoryName: String,
    val title: String,
    val content: String,
    val images: List<String>?,
    val commentCount: Int,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val articleLikeId: Long?,
)

fun ArticleDetailData.toUnit(userId: Long): ArticleDetailUnit =
    ArticleDetailUnit(
        id = this.id,
        userId = this.userId,
        isWriter = userId == this.userId,
        isLiked = this.articleLikeId != null,
        category = CategoryGetResponse(
            id = this.categoryId,
            name = this.categoryName
        ),
        title = this.title,
        content = this.content,
        thumbnail = this.images?.firstOrNull(),
        images = this.images,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        createdAt = this.createdAt.toResponse()
    )
