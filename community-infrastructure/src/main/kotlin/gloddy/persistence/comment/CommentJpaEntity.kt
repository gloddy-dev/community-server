package gloddy.persistence.comment

import gloddy.persistence.article.ArticleJpaEntity
import gloddy.persistence.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
@SQLRestriction("deleted = false")
class CommentJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val article: ArticleJpaEntity,

    @Column(name = "content", nullable = false, columnDefinition = "longtext")
    val content: String,

    @Column(name = "like_count", nullable = false)
    val likeCount: Int,

    @Column(name = "comment_count", nullable = false)
    val commentCount: Int,

    @Column(name = "parent_id", nullable = false)
    val parentId: Long,

    var deleted: Boolean = false,

    createdAt: LocalDateTime? = null
): BaseTimeEntity(
    createdAt = createdAt
)