package gloddy.persistence.article.repository.read

import com.querydsl.core.annotations.QueryProjection
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
