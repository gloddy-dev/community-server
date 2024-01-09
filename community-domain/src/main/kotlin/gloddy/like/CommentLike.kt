package gloddy.like

import gloddy.comment.Comment

@JvmInline
value class CommentLikeId(val value: Long)

@JvmInline
value class ArticleLikeId(val value: Long)

data class CommentLike(
    val id: CommentLikeId? = CommentLikeId(0L),
    val user: User,
    val comment: Comment
)

data class ArticleLike(
    val id: ArticleLikeId? = ArticleLikeId(0L),
    val user: User,
    val article: Article
)