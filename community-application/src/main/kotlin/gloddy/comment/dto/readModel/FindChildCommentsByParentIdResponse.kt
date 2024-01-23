package gloddy.comment.dto.readModel

data class FindChildCommentsByParentIdResponse(
    val childComments: List<FindChildCommentByParentIdResponse>
)