package gloddy.comment

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class CommentLikeNotFoundException : GloddyCommunityException(
    statusCode = 404,
    errorCode = ErrorCode.COMMENT_LIKE_NOT_FOUND,
    message = "존재하지 않는 댓글 좋아요입니다."
)