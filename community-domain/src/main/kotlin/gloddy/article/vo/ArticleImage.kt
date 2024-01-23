package gloddy.article.vo

import gloddy.article.ArticleImageSizeOverException

data class ArticleImage(
    val images: List<String>?
) {

    init {
        verifySize(images)
    }

    private fun verifySize(images: List<String>?) {
        if (!images.isNullOrEmpty() && images.size > 3) {
            throw ArticleImageSizeOverException()
        }
    }
}