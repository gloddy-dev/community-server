package gloddy.persistence.comment.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.comment.QCommentJpaEntity.commentJpaEntity
import gloddy.persistence.comment.QCommentLikeJpaEntity.commentLikeJpaEntity
import gloddy.persistence.comment.model.FindParentCommentsByArticleIdData
import gloddy.persistence.comment.model.QFindParentCommentsByArticleIdData
import gloddy.persistence.comment.repository.CommentJpaCustomRepository
import org.springframework.stereotype.Repository

@Repository
class CommentJpaCustomRepositoryImpl(
    private val query: JPAQueryFactory
): CommentJpaCustomRepository {

    override fun findParentCommentsByArticleId(articleId: Long, userId: Long): List<FindParentCommentsByArticleIdData> {
        return query.select(
            QFindParentCommentsByArticleIdData(
                commentJpaEntity.id,
                commentJpaEntity.userId,
                commentJpaEntity.article.id,
                commentJpaEntity.content,
                commentJpaEntity.likeCount,
                commentJpaEntity.commentCount,
                commentJpaEntity.createdAt,
                commentJpaEntity.updatedAt,
                commentLikeJpaEntity.id
            )
        ).from(commentJpaEntity)
            .leftJoin(commentLikeJpaEntity)
            .on(
                commentJpaEntity.id.eq(commentLikeJpaEntity.comment.id)
                    .and(commentLikeJpaEntity.userId.eq(userId))
            )
            .where(articleIdEq(articleId))
            .fetch()
    }

    private fun articleIdEq(articleId: Long) =
        commentJpaEntity.article.id.eq(articleId)
}