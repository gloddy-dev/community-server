package gloddy.article.exception

import gloddy.article.ArticleErrorCode
import gloddy.core.GloddyCommunityException

class ArticleNotFoundException : GloddyCommunityException(ArticleErrorCode.NOT_FOUND)