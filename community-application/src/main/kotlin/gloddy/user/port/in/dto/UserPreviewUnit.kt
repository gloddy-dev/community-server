package gloddy.user.port.`in`.dto

data class UserPreviewUnit(
    val id: Long,
    val isCertifiedStudent: Boolean,
    val profileImage: String,
    val nickName: String,
    val countryName: String?,
    val countryImage: String?,
    val reliabilityLevel: String
)