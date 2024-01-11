package gloddy.core

interface ErrorCodeBase {
    val statusCode: Int
    val errorCode: String
    val message: String
}