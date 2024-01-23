package gloddy.comment.service

import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.comment.Comment
import gloddy.comment.dto.ChildCommentCreateRequest
import gloddy.comment.dto.CommentCreateResponse
import gloddy.comment.dto.ParentCommentCreateRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentCreateService(
    private val commentCommandPort: CommentCommandPort,
    private val articleQueryPort: ArticleQueryPersistencePort,
) {
    fun createParent(request: ParentCommentCreateRequest): CommentCreateResponse {
        val article = articleQueryPort.findById(request.articleId.value)

        return Comment.parent(
            userId = request.userId,
            article = article,
            content = request.content
        )
        .let {
            commentCommandPort.save(it)
        }
        .run {
            CommentCreateResponse(this)
        }
    }

    fun createChild(request: ChildCommentCreateRequest): CommentCreateResponse {
        val article = articleQueryPort.findById(request.articleId.value)

        return Comment.child(
            userId = request.userId,
            article = article,
            content = request.content,
            parentCommentId = request.parentCommentId
        )
        .let {
            commentCommandPort.save(it)
        }
        .run {
            CommentCreateResponse(this)
        }
    }
}