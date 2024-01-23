package gloddy.article

import gloddy.core.ErrorCode
import gloddy.core.GloddyCommunityException

class ArticleNotFoundException : GloddyCommunityException(
    statusCode = ErrorCode.ARTICLE_NOT_FOUND.statusCode,
    errorCode = ErrorCode.ARTICLE_NOT_FOUND.errorCode,
    message = ErrorCode.ARTICLE_NOT_FOUND.message
)

class ArticleNoAuthorizationException : GloddyCommunityException(
    statusCode = ErrorCode.ARTICLE_NO_AUTHORIZATION.statusCode,
    errorCode = ErrorCode.ARTICLE_NO_AUTHORIZATION.errorCode,
    message = ErrorCode.ARTICLE_NO_AUTHORIZATION.message
)

class ArticleImageSizeOverException : GloddyCommunityException(
    statusCode = ErrorCode.ARTICLE_IMAGE_SIZE_OVER.statusCode,
    errorCode = ErrorCode.ARTICLE_IMAGE_SIZE_OVER.errorCode,
    message = ErrorCode.ARTICLE_IMAGE_SIZE_OVER.message
)