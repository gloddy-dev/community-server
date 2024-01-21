package gloddy.internal.client.payload

import gloddy.user.port.`in`.dto.UserPreviewUnit

data class UserPreviewPayload(
    val id: Long,
    val isCertifiedStudent: Boolean,
    val profileImage: String,
    val nickName: String,
    val countryName: String?,
    val countryImage: String?,
    val reliabilityLevel: String
)

fun UserPreviewPayload.toResponse(): UserPreviewUnit =
    UserPreviewUnit(
        id = this.id,
        isCertifiedStudent = this.isCertifiedStudent,
        profileImage = this.profileImage,
        nickName = this.nickName,
        countryName = this.countryName,
        countryImage = this.countryImage,
        reliabilityLevel = this.reliabilityLevel
    )