package gloddy.user

data class User(
    val id: Long,
    val isCertifiedStudent: Boolean,
    val profileImage: String,
    val nickname: String,
    val countryName: String?,
    val countryImage: String?,
    val reliabilityLevel: String
)