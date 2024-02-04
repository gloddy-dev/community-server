package gloddy.persistence.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserJpaEntity(
    @Id
    val id: Long,
    @Column(nullable = false)
    val isCertifiedStudent: Boolean,
    @Column(nullable = false)
    val profileImage: String,
    @Column(nullable = false)
    val nickname: String,
    val countryName: String?,
    val countryImage: String?,
    @Column(nullable = false)
    val reliabilityLevel: String,
)