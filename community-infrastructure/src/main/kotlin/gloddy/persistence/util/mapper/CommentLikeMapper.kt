package gloddy.persistence.util.mapper

import gloddy.comment.CommentLike
import gloddy.comment.CommentLikeId
import gloddy.core.UserId
import gloddy.persistence.comment.CommentLikeJpaEntity

fun CommentLikeJpaEntity.toDomain(): CommentLike =
    CommentLike(
        id = CommentLikeId(this.id),
        userId = UserId(this.userId),
        comment = this.comment.toDomain(),
    )

fun CommentLike.toEntity(): CommentLikeJpaEntity =
    CommentLikeJpaEntity(
        id = this.id!!.value,
        userId = this.userId.value,
        comment = this.comment.toEntity(),
    )
