package gloddy.article.port.`in`.dto.read

import gloddy.category.port.dto.CategoryReadData

data class ArticleDetailUnit(
    val id: Long,
    val userId: Long,
    val isWriter: Boolean,
    val isLiked: Boolean,
    val category: CategoryReadData,
    val title: String,
    val content: String,
    val thumbnail: String?,
    val images: List<String>?,
    val likeCount: Int,
    val commentCount: Int,
    val createdAt: String
)