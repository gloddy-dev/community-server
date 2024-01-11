package gloddy.persistence.comment.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import gloddy.article.Article
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.comment.QCommentJpaEntity.commentJpaEntity
import gloddy.persistence.comment.QCommentLikeJpaEntity.commentLikeJpaEntity
import gloddy.persistence.comment.model.CommentFindByArticleDto
import gloddy.persistence.comment.model.CommentFindMaxRefDto
import gloddy.persistence.comment.model.QCommentFindByArticleDto
import gloddy.persistence.comment.model.QCommentFindMaxRefDto
import gloddy.persistence.comment.repository.CommentJpaCustomRepository
import org.springframework.stereotype.Repository

@Repository
class CommentJpaCustomRepositoryImpl(
    private val query: JPAQueryFactory
): CommentJpaCustomRepository {

    override fun findAllByArticle(article: ArticleJpaEntity, currentUserId: Long): List<CommentFindByArticleDto> {
        return query
            .select(
                QCommentFindByArticleDto(
                    commentJpaEntity.id,
                    commentJpaEntity.userId,
                    commentJpaEntity.article,
                    commentJpaEntity.likeCount,
                    commentJpaEntity.content,
                    commentJpaEntity.depth,
                    commentJpaEntity.ref,
                    commentJpaEntity.createdAt,
                    commentLikeJpaEntity.userId
                )
            )
            .from(commentJpaEntity)
            .leftJoin(commentLikeJpaEntity)
            .on(commentJpaEntity.id.eq(commentLikeJpaEntity.comment.id))
            .orderBy(commentJpaEntity.createdAt.desc())
            .fetch()
//
//        val total: Long? = query.select(commentJpaEntity.count())
//            .from(commentJpaEntity)
//            .where(articleEq(article))
//            .fetchOne()
//
//        return PageImpl(comments, pageable, total ?: 0)
    }

    override fun findOneByArticleOrderByRefDesc(article: ArticleJpaEntity): CommentFindMaxRefDto? {
        return query.select(
                QCommentFindMaxRefDto(
                    commentJpaEntity.ref
                )
            )
            .from(commentJpaEntity)
            .where(articleEq(article))
            .orderBy(commentJpaEntity.ref.desc())
            .fetchOne()
    }

    private fun articleEq(article: ArticleJpaEntity) =
        commentJpaEntity.article.eq(article)
}