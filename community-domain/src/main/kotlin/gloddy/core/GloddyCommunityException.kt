package gloddy.core

/**
 * @param statusCode HTTP 상태 코드
 * @param errorCode 에러 코드
 * @param message 에러 상세 설명
 */
abstract class GloddyCommunityException(
    open val statusCode: Int,
    open val errorCode: ErrorCode,
    override val message: String?
): RuntimeException(message)
