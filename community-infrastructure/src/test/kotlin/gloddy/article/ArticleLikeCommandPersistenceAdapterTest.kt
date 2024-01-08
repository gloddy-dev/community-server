package gloddy.article

import gloddy.ArticleFixture.*
import gloddy.PersistenceTest
import gloddy.core.UserId
import gloddy.persistence.article.adapter.ArticleLikeCommandPersistenceAdapter
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

@DisplayName("ArticleLikeCommandPersistenceAdapter 클래스의")
class ArticleLikeCommandPersistenceAdapterTest: PersistenceTest() {

    private val USER_ID = 1L
    private lateinit var persistedArticle: Article

    private lateinit var articleLikeCommandPersistenceAdapter: ArticleLikeCommandPersistenceAdapter

    @BeforeEach
    fun setUp() {
        persistedArticle = articleJpaRepository.save(JIHWAN.toDomain(USER_ID, CATEGORY[0]).toEntity()).toDomain()
        articleLikeCommandPersistenceAdapter = ArticleLikeCommandPersistenceAdapter(articleLikeJpaRepository)
    }

    @Nested
    @DisplayName("save 메소드는")
    inner class Save {

        @Test
        @DisplayName("ArticleLike 도메인을 입력받고 DB에 저장하고 Id가 존재하는 ArticleLike를 반환한다.")
        fun success_save_and_returns() {
            //given
            val articleLike = ArticleLike(
                userId = UserId(USER_ID),
                article = persistedArticle
            )

            //when
            val saveArticleLike = articleLikeCommandPersistenceAdapter.save(articleLike)

            //then
            assertNotNull(saveArticleLike.id)
            assertEquals(saveArticleLike.userId.value, USER_ID)
            assertEquals(saveArticleLike.article, persistedArticle)
        }
    }

    @Nested
    @DisplayName("delete 메소드는")
    inner class Delete {

        private lateinit var persistedArticleLike: ArticleLike

        @BeforeEach
        fun given() {
            persistedArticleLike = articleLikeCommandPersistenceAdapter.save(
                ArticleLike(
                    userId = UserId(USER_ID),
                    article = persistedArticle
                )
            )
            flushAndClear()
        }

        @Test
        @DisplayName("영속화 된 ArticleLike를 입력받고 DB에서 삭제한다.")
        fun success_delete() {
            //when
            articleLikeCommandPersistenceAdapter.delete(persistedArticleLike)

            //then
            assertNull(articleLikeJpaRepository.findByIdOrNull(persistedArticleLike.id!!))
        }
    }
}