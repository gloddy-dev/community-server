package gloddy.core

interface ErrorCode {
    val statusCode: Int
    val errorCode: String
    val message: String
}