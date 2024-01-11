package gloddy.article

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class ArticleNotFoundException : GloddyCommunityException(
    statusCode = 404,
    errorCode = ErrorCode.ARTICLE_NOT_FOUND,
    message = "존재하지 않는 게시글입니다."
)

class ArticleNoAuthorizationException : GloddyCommunityException(
    statusCode = 401,
    errorCode = ErrorCode.ARTICLE_NO_AUTHORIZATION,
    message = "해당 게시글에 권한이 없습니다."
)

class ArticleImageSizeOverException : GloddyCommunityException(
    statusCode = 400,
    errorCode = ErrorCode.ARTICLE_IMAGE_SIZE_OVER,
    message = "게시글에 이미지는 최대 3개 입니다."
)