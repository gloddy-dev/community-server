package gloddy.comment

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class CommentNotFoundException : GloddyCommunityException(
    statusCode = ErrorCode.COMMENT_NOT_FOUND.statusCode,
    errorCode = ErrorCode.COMMENT_NOT_FOUND.errorCode,
    message = ErrorCode.COMMENT_NOT_FOUND.message
)

class CommentNotAuthorizationException : GloddyCommunityException(
    statusCode = ErrorCode.COMMENT_NOT_AUTHORIZATION.statusCode,
    errorCode = ErrorCode.COMMENT_NOT_AUTHORIZATION.errorCode,
    message = ErrorCode.COMMENT_NOT_AUTHORIZATION.message
)
