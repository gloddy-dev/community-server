package gloddy.persistence.util.mapper

import gloddy.comment.Comment
import gloddy.core.CommentId
import gloddy.core.UserId
import gloddy.persistence.comment.CommentJpaEntity

fun CommentJpaEntity.toDomain(): Comment =
    Comment(
        id = CommentId(this.id),
        userId = UserId(this.userId),
        article = this.article.toDomain(),
        content = this.content,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        parentId = CommentId(this.parentId),
        createdAt = this.createdAt!!
    )

fun Comment.toEntity(): CommentJpaEntity =
    CommentJpaEntity(
        userId = this.userId.value,
        article = this.article.toEntity(),
        content = this.content,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        parentId = this.parentId.value,
        createdAt = this.createdAt
    )
