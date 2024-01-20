package gloddy.article.port.`in`.dto.read

import gloddy.user.port.`in`.dto.UserPreviewUnit

data class ArticleDetailResponse(
    val article: ArticleDetailUnit,
    val writer: UserPreviewUnit
)