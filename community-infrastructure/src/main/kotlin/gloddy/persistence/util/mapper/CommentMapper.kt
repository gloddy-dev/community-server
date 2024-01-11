package gloddy.persistence.util.mapper

import gloddy.comment.Comment
import gloddy.comment.CommentId
import gloddy.persistence.comment.CommentJpaEntity
import gloddy.user.UserId

fun CommentJpaEntity.toDomain(): Comment =
    Comment(
        id = CommentId(this.id),
        userId = UserId(this.userId),
        article = this.article.toDomain(),
        likeCount = this.likeCount,
        content = this.content,
        depth = this.depth,
        ref = this.ref,
        createdAt = this.createdAt!!,
    )

fun Comment.toEntity(): CommentJpaEntity =
    CommentJpaEntity(
        userId = this.userId.value,
        article = this.article.toEntity(),
        likeCount = this.likeCount,
        content = this.content,
        depth = this.depth,
        ref = this.ref,
    )
