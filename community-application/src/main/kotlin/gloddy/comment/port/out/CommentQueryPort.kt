package gloddy.comment.port.out

import gloddy.comment.Comment
import gloddy.comment.dto.readModel.ParentCommentUnit
import gloddy.core.CommentId


interface CommentQueryPort {
    fun findById(id: CommentId): Comment
    fun findParentComments(articleId: Long, userId: Long): List<ParentCommentUnit>
}