package gloddy.article.port.`in`.dto.read

import gloddy.category.port.`in`.dto.CategoryGetResponse

data class ArticleDetailUnit(
    val id: Long,
    val userId: Long,
    val isWriter: Boolean,
    val isLiked: Boolean,
    val category: CategoryGetResponse,
    val title: String,
    val content: String,
    val thumbnail: String?,
    val images: List<String>?,
    val likeCount: Int,
    val commentCount: Int,
    val createdAt: String
)