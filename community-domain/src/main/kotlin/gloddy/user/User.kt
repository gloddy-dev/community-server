package gloddy.user

@JvmInline
value class UserId(val value: Long)

data class User(
    val id: UserId? = UserId(0L),
    val name: String,
    val reliability: String,
    val country: String,
    val image: String,
    val school: String,
) {
    init {
        require(name.isNotBlank()) { "name must be not blank" }
        require(reliability.isNotBlank()) { "reliability must be not blank" }
        require(country.isNotBlank()) { "country must be not blank" }
        require(image.isNotBlank()) { "image must be not blank" }
    }
}