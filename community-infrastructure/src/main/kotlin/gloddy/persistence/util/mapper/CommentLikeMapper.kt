package gloddy.persistence.util.mapper

import gloddy.comment.CommentLike
import gloddy.comment.CommentLikeId
import gloddy.persistence.comment.CommentLikeJpaEntity
import gloddy.user.UserId

fun CommentLikeJpaEntity.toDomain(): CommentLike =
    CommentLike(
        id = CommentLikeId(this.id),
        userId = UserId(this.userId),
        comment = this.comment.toDomain(),
    )

fun CommentLike.toEntity(): CommentLikeJpaEntity =
    CommentLikeJpaEntity(
        userId = this.userId.value,
        comment = this.comment.toEntity(),
    )
