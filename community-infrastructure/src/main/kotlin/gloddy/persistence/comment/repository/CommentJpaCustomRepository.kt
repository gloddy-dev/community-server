package gloddy.persistence.comment.repository

import gloddy.persistence.comment.model.FindParentCommentsByArticleIdData

interface CommentJpaCustomRepository {
    fun findParentCommentsByArticleId(articleId: Long, userId: Long): List<FindParentCommentsByArticleIdData>
}