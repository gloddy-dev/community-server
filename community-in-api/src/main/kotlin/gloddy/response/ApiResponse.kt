package gloddy.response

data class ApiResponse<T>(
    val meta: Meta,
    val data: T?
) {
    data class Meta(
        val statusCode: Int,
        val message: String?
    )
}