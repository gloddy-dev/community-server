package gloddy.core

open class GloddyCommunityBaseException(code: ErrorCodeBase) : RuntimeException() {

    val statusCode: Int
    val errorCode: String
    override val message: String

    init {
        statusCode = code.statusCode
        errorCode = code.errorCode
        message = code.message
    }
}