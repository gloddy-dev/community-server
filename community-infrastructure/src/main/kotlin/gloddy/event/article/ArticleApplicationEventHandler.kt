package gloddy.event.article

import gloddy.article.port.`in`.ArticleCommandUseCase
import gloddy.article.port.`in`.dto.command.ArticleUpsertCommentRequest
import gloddy.article.port.`in`.dto.command.CommentStatus
import gloddy.comment.event.ParentCommentCreateEvent
import gloddy.comment.event.ParentCommentDeleteEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ArticleApplicationEventHandler(
    private val articleCommandUseCase: ArticleCommandUseCase
) {

    @EventListener
    fun handleParentCommentCreateEvent(event: ParentCommentCreateEvent) {
        articleCommandUseCase.upsertComment(
            ArticleUpsertCommentRequest(
                articleId = event.articleId,
                status = CommentStatus.CREATE
            )
        )
    }

    @EventListener
    fun handleParentCommentDeleteEvent(event: ParentCommentDeleteEvent) {
        articleCommandUseCase.upsertComment(
            ArticleUpsertCommentRequest(
                articleId = event.articleId,
                status = CommentStatus.DELETE
            )
        )
    }
}