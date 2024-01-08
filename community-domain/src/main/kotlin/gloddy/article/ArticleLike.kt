package gloddy.article

import gloddy.core.UserId

data class ArticleLike(
    val userId: UserId,
    val article: Article,
    val id: Long? = null,
) {
    init {
        verifyArticle(article)
    }

    private fun verifyArticle(article: Article) {
        if (article.id == null) {
            throw RuntimeException("ArticleLike 생성 오류 : 영속화 되지 않은 Article을 입력으로 받았습니다.")
        }
    }
}
