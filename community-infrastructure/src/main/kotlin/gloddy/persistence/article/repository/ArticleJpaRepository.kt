package gloddy.persistence.article.repository

import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.article.repository.custom.ArticleJpaRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long>, ArticleJpaRepositoryCustom {
}