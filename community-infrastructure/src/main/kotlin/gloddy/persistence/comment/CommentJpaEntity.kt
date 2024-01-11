package gloddy.persistence.comment

import gloddy.persistence.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L,

    @Column(name = "user_id", nullable = false)
    private val userId: Long,

    @Column(name = "article_id", nullable = false)
    private val articleId: Long,

    @Column(name = "like_count", nullable = false)
    private val likeCount: Long,

    @Column(name = "depth", nullable = false)
    private val depth: Int,

    @Column(name = "ref", nullable = false)
    private val ref: Int

): BaseTimeEntity()