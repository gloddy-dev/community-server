package gloddy.persistence.util.mapper

import gloddy.persistence.user.UserJpaEntity
import gloddy.user.User

fun User.toEntity(): UserJpaEntity =
    UserJpaEntity(
        id = this.id,
        isCertifiedStudent = this.isCertifiedStudent,
        profileImage = this.profileImage,
        nickname = this.nickname,
        countryName = this.countryName,
        countryImage = this.countryImage,
        reliabilityLevel = this.reliabilityLevel
    )

fun UserJpaEntity.toDomain(): User =
    User(
        id = this.id,
        isCertifiedStudent = this.isCertifiedStudent,
        profileImage = this.profileImage,
        nickname = this.nickname,
        countryName = this.countryName,
        countryImage = this.countryImage,
        reliabilityLevel = this.reliabilityLevel
    )