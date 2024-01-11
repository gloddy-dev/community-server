package gloddy.exception

import gloddy.core.GloddyCommunityBaseException
import gloddy.core.GloddyCommunityException
import gloddy.response.ApiResponseEntityWrapper
import gloddy.response.CommunityApiResponse
import gloddy.response.fail
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["gloddy"])
class CommunityControllerAdvice {

    companion object {
        private const val INTERNAL_SERVER_ERROR_CODE = 500
        private const val INTERNAL_SERVER_ERROR_MESSAGE = "서버 내부 오류입니다."
        private val logger = LoggerFactory.getLogger(CommunityControllerAdvice::class.java)
    }

    @ExceptionHandler(GloddyCommunityException::class)
    fun handleGloddyCummunityException(e: GloddyCommunityBaseException): ResponseEntity<CommunityApiResponse<Nothing>> {
        logger.error("Community Error\n{}", e.message, e)
        return ApiResponseEntityWrapper<Nothing>().fail(e.statusCode, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleInternalServerError(e: Exception): ResponseEntity<CommunityApiResponse<Nothing>> {
        logger.error("Community Internal Error\n{}", e.message, e)
        return ApiResponseEntityWrapper<Nothing>().fail(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MESSAGE)
    }
}