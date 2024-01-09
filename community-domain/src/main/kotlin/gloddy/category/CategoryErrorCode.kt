package gloddy.category

import gloddy.core.ErrorCode

enum class CategoryErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val message: String
): ErrorCode {
    NOT_FOUND(400, "CATEGORY_001", "해당 카테고리를 찾을 수 없습니다.")
}