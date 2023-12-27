package gloddy.article.vo

import gloddy.core.UserId

data class Like(
    val likes: List<UserId>
)