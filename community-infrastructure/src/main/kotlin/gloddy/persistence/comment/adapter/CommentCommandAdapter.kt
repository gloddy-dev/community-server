package gloddy.persistence.comment.adapter

import gloddy.comment.Comment
import gloddy.comment.CommentNotFoundException
import gloddy.comment.event.CommentEvent
import gloddy.comment.port.out.CommentCommandPort
import gloddy.persistence.comment.CommentJpaEntity
import gloddy.persistence.comment.repository.CommentJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CommentCommandAdapter(
    private val commentJpaRepository: CommentJpaRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
): CommentCommandPort {
    override fun save(comment: Comment): Comment {
        val saveComment = commentJpaRepository
            .save(comment.toEntity())
            .toDomain()
        publishEvents(saveComment, comment.events)
        return saveComment
    }

    override fun delete(comment: Comment) {
        find(comment.id!!.value).changeDeletedToTrue()
        publishEvents(comment, comment.events)
    }

    fun find(id: Long): CommentJpaEntity =
        commentJpaRepository.findByIdOrNull(id) ?: throw CommentNotFoundException()

    private fun publishEvents(persistedComment: Comment, events: List<CommentEvent>) {
        events.forEach {
            if (it.commentId == 0L) {
                it.commentId = persistedComment.id!!.value
            }
            applicationEventPublisher.publishEvent(it)
        }
    }
}