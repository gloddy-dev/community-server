package gloddy.comment.dto.readModel

import java.time.LocalDateTime

data class ParentCommentUnit(
    val id: Long,
    val isWriter: Boolean,
    val isLiked: Boolean,
    val userId: Long,
    val articleId: Long,
    val content: String,
    val likeCount: Int,
    val commentCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)