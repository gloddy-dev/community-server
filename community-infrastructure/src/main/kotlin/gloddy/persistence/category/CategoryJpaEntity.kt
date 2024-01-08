package gloddy.persistence.category

import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryJpaEntity(

    var name: String,

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}