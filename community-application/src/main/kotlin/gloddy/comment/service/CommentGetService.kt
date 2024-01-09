package gloddy.comment.service

import gloddy.comment.Comment
import gloddy.comment.dto.CommentGetRequest
import gloddy.comment.port.out.CommentQueryPort
import org.springframework.stereotype.Service

@Service
class CommentGetService(
    private val commentQueryPort: CommentQueryPort
) {
    fun getByArticle(dto: CommentGetRequest) {
        val comments = commentQueryPort.findAllByArticleId(dto.articleId)

        val commentGroupByRef: Map<Int, List<Comment>> = comments.groupBy { it.ref }
            .map {  }
        commentGroupByRef.
    }
}