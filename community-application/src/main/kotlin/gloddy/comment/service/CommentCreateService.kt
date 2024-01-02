package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.dto.ChildCommentCreateDto
import gloddy.comment.dto.ParentCommentCreateDto
import gloddy.comment.port.out.CommentCreatePort
import gloddy.comment.port.out.CommentGetPort
import org.springframework.stereotype.Service

@Service
class CommentCreateService(
    private val commentCreatePort: CommentCreatePort,
    private val commentGetPort: CommentGetPort
) {
    fun createParent(dto: ParentCommentCreateDto) {
        val ref = commentGetPort.getMaxRef().maxRef + 1

        with(dto) {
            Comment.init(
                userId = userId,
                articleId = articleId,
                content = content,
                depth = 0,
                ref = ref
            )
        }.run { commentCreatePort.create(this) }
    }

    fun createParent(dto: ChildCommentCreateDto) {
        val parentInfo = commentGetPort.getParentRef()
        val depth = parentInfo.depth + 1

        with(dto) {
            Comment.init(
                userId = userId,
                articleId = articleId,
                content = content,
                depth = depth,
                ref = parentInfo.ref
            )
        }.run { commentCreatePort.create(this) }
    }
}