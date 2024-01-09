package gloddy.comment.port.out

import gloddy.comment.ArticleId
import gloddy.comment.Comment
import gloddy.comment.CommentId
import gloddy.comment.dto.readModel.CommentParentInfoDto
import gloddy.comment.dto.readModel.CommentRefDto


interface CommentQueryPort {
    fun getMaxRef(): CommentRefDto
    fun getParentRef(): CommentParentInfoDto
    fun findById(id: CommentId): Comment

    fun findAllByArticleId(articleId: ArticleId): List<Comment>
}