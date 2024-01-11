package gloddy.comment.service

import gloddy.article.port.out.ArticleQueryPersistencePort
import gloddy.comment.Comment
import gloddy.comment.dto.ChildCommentCreateRequest
import gloddy.comment.dto.CommentCreateResponse
import gloddy.comment.dto.ParentCommentCreateRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.user.port.out.UserQueryPort
import org.springframework.stereotype.Service

@Service
class CommentCreateService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort,
    private val articleGetPort: ArticleQueryPersistencePort,
) {
    fun createParent(dto: ParentCommentCreateRequest): CommentCreateResponse {
        val article = articleGetPort.findById(dto.articleId.value)
        val ref = commentQueryPort.findMaxRefByArticle(article).maxRef + 1

        return Comment(
            userId = dto.userId,
            article = article,
            content = dto.content,
            depth = 0,
            ref = ref
        )
        .let {
            commentCommandPort.save(it)
        }
        .run {
            CommentCreateResponse(this)
        }
    }

    fun createChild(dto: ChildCommentCreateRequest): CommentCreateResponse {
        val article = articleGetPort.findById(dto.articleId.value)
        val parentInfo = commentQueryPort.findById(dto.parentCommentId)
        val depth = parentInfo.depth + 1

        return Comment(
            userId = dto.userId,
            article = article,
            content = dto.content,
            depth = depth,
            ref = parentInfo.ref
        )
        .let {
            commentCommandPort.save(it)
        }
        .run {
            CommentCreateResponse(this)
        }
    }
}