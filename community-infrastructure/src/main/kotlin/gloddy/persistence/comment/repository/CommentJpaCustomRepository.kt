package gloddy.persistence.comment.repository

import gloddy.persistence.comment.model.FindChildCommentsByParentIdData
import gloddy.persistence.comment.model.FindParentCommentsByArticleIdData

interface CommentJpaCustomRepository {
    fun findParentCommentsByArticleId(articleId: Long, userId: Long): List<FindParentCommentsByArticleIdData>
    fun findChildCommentsByParentId(parentId: Long, userId: Long): List<FindChildCommentsByParentIdData>
}