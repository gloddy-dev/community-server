package gloddy.article

import gloddy.ArticleFixture
import gloddy.PersistenceTest
import gloddy.persistence.article.adapter.ArticleCommandPersistenceAdapter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("ArticleCommandPersistenceAdapterTest의")
class ArticleCommandPersistenceAdapterTest : PersistenceTest() {

    private val USER_ID = 1L
    private lateinit var article: Article

    private lateinit var articleCommandPersistenceAdapter: ArticleCommandPersistenceAdapter

    @BeforeEach
    fun setUp() {
        article = ArticleFixture.JIHWAN.toDomain(USER_ID, CATEGORY[0])
        articleCommandPersistenceAdapter = ArticleCommandPersistenceAdapter(articleJpaRepository)
    }

    @Nested
    @DisplayName("save 메소드는")
    inner class Save {

        @Test
        @DisplayName("Article 도메인 입력을 받으면 DB에 저장하고 반환한다.")
        fun success_save_and_returns() {
            //given & when
            val savedArticle = articleCommandPersistenceAdapter.save(article)

            //then
            assertNotNull(savedArticle.id)
            assertEquals(savedArticle.userId.value, USER_ID)
            assertEquals(savedArticle.commentCount, 0)
            assertEquals(savedArticle.likeCount, 0)
        }
    }
}