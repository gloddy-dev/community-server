package gloddy.persistence.article.repository

import gloddy.persistence.article.ArticleLikeJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleLikeJpaRepository : JpaRepository<ArticleLikeJpaEntity, Long> {
}