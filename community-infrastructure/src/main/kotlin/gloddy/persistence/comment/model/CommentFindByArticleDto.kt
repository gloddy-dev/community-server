package gloddy.persistence.comment.model

import com.querydsl.core.annotations.QueryProjection
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.util.mapper.toDomain
import java.time.LocalDateTime
import gloddy.comment.dto.readModel.CommentFindByArticleDto as CommentFindByArticleResponseDto

data class CommentFindByArticleDto @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val article: ArticleJpaEntity,
    val likeCount: Long,
    val content: String,
    val depth: Int,
    val ref: Int,
    val createdAt: LocalDateTime,
    val likedUserId: Long?
)

fun CommentFindByArticleDto.toResponseDto(): CommentFindByArticleResponseDto =
    CommentFindByArticleResponseDto(
        id = this.id,
        userId = this.userId,
        article = this.article.toDomain(),
        likeCount = this.likeCount,
        content = this.content,
        depth = this.depth,
        ref = this.ref,
        createdAt = this.createdAt,
        likedUserId = this.likedUserId
    )