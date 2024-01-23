package gloddy.comment

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class CommentLikeNotFoundException : GloddyCommunityException(
    statusCode = ErrorCode.COMMENT_LIKE_NOT_FOUND.statusCode,
    errorCode = ErrorCode.COMMENT_LIKE_NOT_FOUND.errorCode,
    message = ErrorCode.COMMENT_LIKE_NOT_FOUND.message
)