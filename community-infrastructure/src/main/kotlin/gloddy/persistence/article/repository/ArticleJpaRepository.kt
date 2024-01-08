package gloddy.persistence.article.repository

import gloddy.persistence.article.ArticleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long> {
}