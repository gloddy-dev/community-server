package gloddy.persistence.article

import gloddy.persistence.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "article_like")
class ArticleLikeJpaEntity(
    val userId: Long,
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "article_id")
    val article: ArticleJpaEntity,
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseTimeEntity() {
}