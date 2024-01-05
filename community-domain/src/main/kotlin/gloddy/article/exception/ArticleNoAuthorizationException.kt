package gloddy.article.exception

import gloddy.article.ArticleErrorCode
import gloddy.core.GloddyCommunityException

class ArticleNoAuthorizationException : GloddyCommunityException(ArticleErrorCode.NO_AUTHORIZATION)