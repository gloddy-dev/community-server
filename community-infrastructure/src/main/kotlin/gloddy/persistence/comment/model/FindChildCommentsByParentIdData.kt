package gloddy.persistence.comment.model

import com.querydsl.core.annotations.QueryProjection
import gloddy.comment.dto.readModel.ChildCommentUnit
import java.time.LocalDateTime

data class FindChildCommentsByParentIdData @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val articleId: Long,
    val parentId: Long,
    val content: String,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val commentLikeId: Long?
)

fun FindChildCommentsByParentIdData.toResponse(userId: Long) =
    ChildCommentUnit(
        id = this.id,
        isWriter = this.userId == userId,
        isLiked = this.commentLikeId != null,
        userId = this.userId,
        articleId = this.articleId,
        parentId = this.parentId,
        content = this.content,
        likeCount = this.likeCount,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
