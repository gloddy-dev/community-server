package gloddy.user.port.`in`.dto

import gloddy.user.User

data class UserSaveRequest(
    val id: Long,
    val isCertifiedStudent: Boolean,
    val profileImage: String,
    val nickName: String,
    val countryName: String?,
    val countryImage: String?,
    val reliabilityLevel: String
)

fun UserSaveRequest.toDomain(): User =
    User(
        id = this.id,
        isCertifiedStudent = this.isCertifiedStudent,
        profileImage = this.profileImage,
        nickname = this.nickName,
        countryName = this.countryName,
        countryImage = this.countryImage,
        reliabilityLevel = this.reliabilityLevel
    )
