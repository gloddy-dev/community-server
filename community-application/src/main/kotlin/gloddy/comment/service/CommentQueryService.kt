package gloddy.comment.service

import gloddy.comment.dto.ChildCommentGetRequest
import gloddy.comment.dto.ParentCommentGetRequest
import gloddy.comment.dto.readModel.FindChildCommentByParentIdResponse
import gloddy.comment.dto.readModel.FindChildCommentsByParentIdResponse
import gloddy.comment.dto.readModel.FindParentCommentByArticleIdResponse
import gloddy.comment.dto.readModel.FindParentCommentsByArticleIdResponse
import gloddy.comment.port.out.CommentQueryPort
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Service

@Service
class CommentQueryService(
    private val commentQueryPort: CommentQueryPort,
    private val userQueryPort: UserQueryPort
) {

    fun getParentComments(request: ParentCommentGetRequest): FindParentCommentsByArticleIdResponse {

        val parentCommentUnits = commentQueryPort.findParentComments(
            articleId = request.articleId.value,
            userId = request.userId.value
        )
        val userPreviewUnits = userQueryPort.getUserPreviewUnits(
            parentCommentUnits.map { it.userId }.toSet()
        )

        return parentCommentUnits.map {
            FindParentCommentByArticleIdResponse(
                comment = it,
                writer = userPreviewUnits[it.userId]!!
            )
        }.let { FindParentCommentsByArticleIdResponse(it) }
    }

    fun getChildComments(request: ChildCommentGetRequest): FindChildCommentsByParentIdResponse {

        val childCommentUnits = commentQueryPort.findChildComments(
            parentId = request.parentId,
            userId = request.userId
        )
        val userPreviewUnits = userQueryPort.getUserPreviewUnits(
            childCommentUnits.map { it.userId }.toSet()
        )

        return childCommentUnits.map {
            FindChildCommentByParentIdResponse(
                childComment = it,
                writer = userPreviewUnits[it.userId]!!
            )
        }.let { FindChildCommentsByParentIdResponse(it) }
    }
}