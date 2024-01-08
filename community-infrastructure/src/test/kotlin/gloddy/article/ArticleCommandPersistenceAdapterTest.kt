package gloddy.article

import gloddy.ArticleFixture
import gloddy.PersistenceTest
import gloddy.article.exception.ArticleNotFoundException
import gloddy.persistence.article.adapter.ArticleCommandPersistenceAdapter
import gloddy.persistence.util.mapper.toEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import kotlin.properties.Delegates

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

    @Nested
    @DisplayName("delete 메소드는")
    inner class Delete {

        private var articleId by Delegates.notNull<Long>()

        @BeforeEach
        fun given() {
            val saveArticle = articleJpaRepository.save(article.toEntity())
            articleId = saveArticle.id!!
            flushAndClear()
        }

        @Test
        @DisplayName("articleId를 입력 받으면 해당 Article을 DB에서 조회하고 deleted 필드를 true로 변경 후 DB에 반영한다.")
        fun success_delete() {
            //when
            articleCommandPersistenceAdapter.delete(articleId)
            flushAndClear()

            //then
            assertThrows(ArticleNotFoundException::class.java) { articleCommandPersistenceAdapter.find(articleId) }
        }
    }
}