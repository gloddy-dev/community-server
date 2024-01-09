package gloddy.like.service

import gloddy.comment.Comment
import gloddy.comment.port.out.CommentCommandPort
import gloddy.comment.port.out.CommentQueryPort
import gloddy.like.CommentLike
import gloddy.like.dto.CommentLikeCreateRequest
import gloddy.like.port.out.CommentLikeCommandPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentLikeCreateService(
    private val likeCreatePort: CommentLikeCommandPort,
    private val commentQueryPort: CommentQueryPort,
    private val commentCommandPort: CommentCommandPort
    private val userGetPort: UserGetPort
) {

    @Transactional
    fun create(dto: CommentLikeCreateRequest) {
        val user = userGetPort.findById(dto.userId)
        val comment = commentQueryPort.findById(dto.commentId)

        CommentLike(
            user = user,
            comment = comment
        )
        .also { increaseLikeCount(comment) }
        .run { likeCreatePort.create(this) }
    }

    fun increaseLikeCount(comment: Comment) {
        val newLikeCount = comment.likeCount + 1
        val newComment = comment.copy(likeCount = newLikeCount)

        commentCommandPort.save(newComment)
    }
}