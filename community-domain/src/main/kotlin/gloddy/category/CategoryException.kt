package gloddy.category

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class CategoryNotFoundException : GloddyCommunityException(
    statusCode = ErrorCode.CATEGORY_NOT_FOUND.statusCode,
    errorCode = ErrorCode.CATEGORY_NOT_FOUND.errorCode,
    message = ErrorCode.CATEGORY_NOT_FOUND.message
)