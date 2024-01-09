package gloddy.comment

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class CommentNotFoundException : GloddyCommunityException(
    statusCode = 404,
    errorCode = ErrorCode.COMMENT_NOT_FOUND,
    message = "존재하지 않는 댓글입니다."
)

class CommentNotAuthorizationException : GloddyCommunityException(
    statusCode = 401,
    errorCode = ErrorCode.COMMENT_NOT_AUTHORIZATION,
    message = "해당 댓글에 권한이 없습니다."
)
