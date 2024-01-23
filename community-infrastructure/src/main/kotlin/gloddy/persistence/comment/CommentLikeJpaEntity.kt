package gloddy.persistence.comment

import gloddy.persistence.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment_like")
class CommentLikeJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    val comment: CommentJpaEntity
) : BaseTimeEntity() {
}