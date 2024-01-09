package gloddy.comment.dto

import gloddy.comment.Comment

data class CommentGetResponse(
    val comments: List<CommentUnit>
)

data class CommentUnit(
    val commentId: Long,
    val content: String,
    val depth: Int,
    val likeCount: Long,
    val commentCount: Long,
    val isLiked: Boolean,
    val user: UserUnit
)

data class UserUnit(
    val userId: Long,
    val name: String,
    val imageUrl: String,
    val reliabilityLevel: String,
    val country: UserCountry
)

data class UserCountry(
    val name: String,
    val imageUrl: String
)