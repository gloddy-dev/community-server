package gloddy.persistence.article.repository.impl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import gloddy.article.port.`in`.ArticleOrder
import gloddy.persistence.article.QArticleJpaEntity.*
import gloddy.persistence.article.QArticleLikeJpaEntity.*
import gloddy.persistence.article.repository.custom.ArticleJpaRepositoryCustom
import gloddy.persistence.article.repository.read.ArticleDetailData
import gloddy.persistence.article.repository.read.QArticleDetailData
import gloddy.persistence.category.QCategoryJpaEntity.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ArticleJpaRepositoryImpl(
    private val query: JPAQueryFactory
) : ArticleJpaRepositoryCustom {

    override fun findDetailArticlePageByCategoryId(categoryId: Long?, userId: Long, pageable: Pageable, order: ArticleOrder)
    : Page<ArticleDetailData> {

        val articleDetailDataPage = query.select(
            QArticleDetailData(
                articleJpaEntity.id,
                articleJpaEntity.userId,
                articleJpaEntity.category.id,
                articleJpaEntity.category.name,
                articleJpaEntity.title,
                articleJpaEntity.content,
                articleJpaEntity.images,
                articleJpaEntity.commentCount,
                articleJpaEntity.likeCount,
                articleJpaEntity.createdAt,
                articleJpaEntity.updatedAt,
                articleLikeJpaEntity.id
            )
        ).from(articleJpaEntity)
            .innerJoin(articleJpaEntity.category, categoryJpaEntity)
            .leftJoin(articleLikeJpaEntity)
            .on(
                articleJpaEntity.id.eq(articleLikeJpaEntity.article.id)
                    .and(articleLikeJpaEntity.userId.eq(userId))
            )
            .where(eqCategoryIdOrNull(categoryId))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(articleOrder(order))
            .fetch()

        val totalCount = query.select(articleJpaEntity.count())
            .from(articleJpaEntity)
            .where(eqCategoryIdOrNull(categoryId))
            .fetchOne()

        return PageImpl(
            articleDetailDataPage,
            pageable,
            totalCount!!
        )
    }

    private fun eqCategoryIdOrNull(categoryId: Long?): BooleanExpression? {
        return categoryId?.let {
            articleJpaEntity.category.id.eq(it)
        }
    }

    private fun articleOrder(order: ArticleOrder): OrderSpecifier<*> {
      return when(order) {
          ArticleOrder.LATEST -> articleJpaEntity.createdAt.desc()
      }
    }
}