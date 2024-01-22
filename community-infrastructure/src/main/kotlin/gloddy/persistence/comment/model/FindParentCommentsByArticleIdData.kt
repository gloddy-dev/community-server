package gloddy.persistence.comment.model

import com.querydsl.core.annotations.QueryProjection
import gloddy.comment.dto.readModel.FindParentCommentByArticleIdResponse
import gloddy.comment.dto.readModel.ParentCommentUnit
import java.time.LocalDateTime

data class FindParentCommentsByArticleIdData @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val articleId: Long,
    val content: String,
    val likeCount: Int,
    val commentCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val commentLikeId: Long?
)

fun FindParentCommentsByArticleIdData.toResponse(userId: Long): ParentCommentUnit =
    ParentCommentUnit(
        id = this.id,
        isWriter = this.userId == userId,
        isLiked = this.commentLikeId != null,
        userId = this.userId,
        articleId = this.articleId,
        content = this.content,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )