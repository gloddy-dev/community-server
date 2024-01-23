package gloddy.comment

import gloddy.core.UserId

@JvmInline
value class CommentLikeId(val value: Long)

data class CommentLike(
    val id: CommentLikeId? = CommentLikeId(0L),
    val userId: UserId,
    val comment: Comment
)