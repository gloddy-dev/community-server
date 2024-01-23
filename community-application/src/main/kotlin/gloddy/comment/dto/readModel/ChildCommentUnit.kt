package gloddy.comment.dto.readModel

import java.time.LocalDateTime

data class ChildCommentUnit(
    val id: Long,
    val isWriter: Boolean,
    val isLiked: Boolean,
    val userId: Long,
    val articleId: Long,
    val parentId: Long,
    val content: String,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)