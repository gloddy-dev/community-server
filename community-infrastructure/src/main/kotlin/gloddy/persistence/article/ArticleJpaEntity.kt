package gloddy.persistence.article

import gloddy.persistence.category.CategoryJpaEntity
import gloddy.persistence.common.BaseTimeEntity
import gloddy.persistence.util.converter.StringArrayConverter
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "article")
@SQLRestriction("deleted = false")
class ArticleJpaEntity(

    val userId: Long,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "category_id")
    var category: CategoryJpaEntity,

    var title: String,

    @field:Column(name = "content", columnDefinition = "longtext")
    var content: String,

    @field:Convert(converter = StringArrayConverter::class)
    @field:Column(name = "images", columnDefinition = "longtext")
    var images: List<String>?,

    var commentCount: Int,

    var likeCount: Int,

    var deleted: Boolean = false,

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {
    fun changeDeletedToTrue() {
        this.deleted = true
    }
}