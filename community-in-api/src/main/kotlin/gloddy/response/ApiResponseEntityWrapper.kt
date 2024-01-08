package gloddy.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponseEntityWrapper<T>(
    val data: T?
) {
    fun getResponseEntity(statusCode: Int, message: String? = null): ResponseEntity<CommunityApiResponse<T>> =
        ResponseEntity.status(statusCode)
            .body(CommunityApiResponse(
                meta = CommunityApiResponse.Meta(
                    statusCode = statusCode,
                    message = message
                ),
                data = this.data
            ))
}

fun <T> ApiResponseEntityWrapper<T>.ok(): ResponseEntity<CommunityApiResponse<T>> = this.getResponseEntity(HttpStatus.OK.value())
fun <T> ApiResponseEntityWrapper<T>.created(): ResponseEntity<CommunityApiResponse<T>> = this.getResponseEntity(HttpStatus.CREATED.value())
fun <T> ApiResponseEntityWrapper<T>.noContent(): ResponseEntity<CommunityApiResponse<T>> = this.getResponseEntity(HttpStatus.NO_CONTENT.value())
fun <T> ApiResponseEntityWrapper<T>.fail(statusCode: Int, message: String) = this.getResponseEntity(statusCode, message)
