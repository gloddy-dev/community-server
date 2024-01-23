package gloddy.persistence.comment.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.comment.QCommentJpaEntity.commentJpaEntity
import gloddy.persistence.comment.QCommentLikeJpaEntity.commentLikeJpaEntity
import gloddy.persistence.comment.model.FindChildCommentsByParentIdData
import gloddy.persistence.comment.model.FindParentCommentsByArticleIdData
import gloddy.persistence.comment.model.QFindChildCommentsByParentIdData
import gloddy.persistence.comment.model.QFindParentCommentsByArticleIdData
import gloddy.persistence.comment.repository.CommentJpaCustomRepository
import org.springframework.stereotype.Repository

@Repository
class CommentJpaCustomRepositoryImpl(
    private val query: JPAQueryFactory,
) : CommentJpaCustomRepository {

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
            .where(articleIdEq(articleId), isParent())
            .fetch()
    }

    override fun findChildCommentsByParentId(parentId: Long, userId: Long): List<FindChildCommentsByParentIdData> {
        return query.select(
            QFindChildCommentsByParentIdData(
                commentJpaEntity.id,
                commentJpaEntity.userId,
                commentJpaEntity.article.id,
                commentJpaEntity.parentId,
                commentJpaEntity.content,
                commentJpaEntity.likeCount,
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
            .where(parentIdEq(parentId), isChild())
            .fetch()
    }

    private fun articleIdEq(articleId: Long) =
        commentJpaEntity.article.id.eq(articleId)

    private fun parentIdEq(parentId: Long) =
        commentJpaEntity.parentId.eq(parentId)


    private fun isParent() =
        commentJpaEntity.parentId.eq(0L)

    private fun isChild() =
        commentJpaEntity.parentId.ne(0L)
}