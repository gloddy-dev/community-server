package gloddy.comment.dto.readModel

data class FindParentCommentsByArticleIdResponse(
    val comments: List<FindParentCommentByArticleIdResponse>
)