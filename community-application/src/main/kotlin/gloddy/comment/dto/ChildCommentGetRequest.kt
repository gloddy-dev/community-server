package gloddy.comment.dto

data class ChildCommentGetRequest(
    val parentId: Long,
    val userId: Long
)