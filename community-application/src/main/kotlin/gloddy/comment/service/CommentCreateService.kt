package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.dto.ChildCommentCreateRequest
import gloddy.comment.dto.ParentCommentCreateRequest
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentCreateService(
    private val commentCommandPort: CommentCommandPort,
    private val commentQueryPort: CommentQueryPort,
    private val articleGetPort: ArticleGetPort,
    private val userGetPort: UserGetPort
) {
    fun createParent(dto: ParentCommentCreateRequest) {
        val article = articleGetPort.findById(dto.articleId)
        val user = userGetPort.findById(dto.userId)

        val ref = commentQueryPort.getMaxRef().maxRef + 1

        Comment(
            user = user,
            article = article,
            content = dto.content,
            depth = 0,
            ref = ref
        ).run { commentCommandPort.save(this) }
    }

    fun createChild(dto: ChildCommentCreateRequest) {
        val article = articleGetPort.findById(dto.articleId)
        val user = userGetPort.findById(dto.userId)

        val parentInfo = commentQueryPort.getParentRef()
        val depth = parentInfo.depth + 1

        Comment(
            user = user,
            article = article,
            content = dto.content,
            depth = depth,
            ref = parentInfo.ref
        ).run { commentCommandPort.save(this) }
    }
}