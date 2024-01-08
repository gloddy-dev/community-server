package gloddy.article

import gloddy.article.exception.ArticleImageSizeOverException
import gloddy.article.vo.ArticleImage
import gloddy.core.UserId
import gloddy.CategoryFixture.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Article 도메인 생성을")
class ArticleCreateTest {

    companion object {
        @JvmStatic
        fun articleProvider() = listOf(
            Arguments.of(listOf("image1")),
            Arguments.of(null)
        )
    }

    @ParameterizedTest
    @MethodSource("articleProvider")
    @DisplayName("성공한다.")
    fun success(image: List<String>?) {

        val category = K_POP.toPersistDomain(1L)

        val article = Article(
            userId = UserId(1L),
            category = category,
            title = "title",
            content = "content",
            images = image
        )

        assertEquals(article.category, category)
        assertEquals(article.image, ArticleImage(image))
        assertEquals(article.commentCount, 0)
        assertEquals(article.likeCount, 0)
    }

    @Test
    @DisplayName("이미지가 3개 초과하여 실패한다.")
    fun fail_by_images_size_over() {
        assertThrows(ArticleImageSizeOverException::class.java) {
            Article(
                userId = UserId(1L),
                category = K_POP.toPersistDomain(1L),
                title = "title",
                content = "content",
                images = listOf("image1", "image2", "image3", "image4")
            )
        }
    }
}