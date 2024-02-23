package gloddy.user

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class UserNotFoundException : GloddyCommunityException(
    statusCode = ErrorCode.USER_NOT_FOUND.statusCode,
    errorCode = ErrorCode.USER_NOT_FOUND.errorCode,
    message = ErrorCode.USER_NOT_FOUND.message
)