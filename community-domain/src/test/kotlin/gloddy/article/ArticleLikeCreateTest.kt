package gloddy.article

import gloddy.core.UserId
import gloddy.ArticleFixture.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게시글 좋아요 도메인 생성을")
class ArticleLikeCreateTest {

    @Test
    @DisplayName("성공한다.")
    fun success() {

        val article = JIHWAN.toPersistDomain(1L, 1L)

        val articleLike = ArticleLike(
            userId = UserId(1L),
            article = article
        )

        assertEquals(articleLike.article, article)
    }
}