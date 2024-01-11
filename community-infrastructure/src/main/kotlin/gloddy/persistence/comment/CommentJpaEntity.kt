package gloddy.persistence.comment

import gloddy.article.Article
import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.common.BaseTimeEntity
import gloddy.user.User
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val article: ArticleJpaEntity,

    @Column(name = "like_count", nullable = false)
    val likeCount: Long,

    @Column(name = "content", nullable = false)
    @Lob
    val content: String,

    @Column(name = "depth", nullable = false)
    val depth: Int,

    @Column(name = "ref", nullable = false)
    val ref: Int,
): BaseTimeEntity()