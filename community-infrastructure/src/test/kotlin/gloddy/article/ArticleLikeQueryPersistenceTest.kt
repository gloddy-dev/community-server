package gloddy.article

import gloddy.ArticleFixture
import gloddy.PersistenceTest
import gloddy.core.UserId
import gloddy.persistence.article.adapter.ArticleLikeQueryPersistenceAdapter
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("ArticleLikeQueryPersistence 클래스의")
class ArticleLikeQueryPersistenceTest: PersistenceTest() {

    private val USER_ID = 1L
    private lateinit var persistedArticle: Article

    private lateinit var articleLikeQueryPersistenceAdapter: ArticleLikeQueryPersistenceAdapter

    @BeforeEach
    fun setUp() {
        persistedArticle = articleJpaRepository.save(ArticleFixture.JIHWAN.toDomain(USER_ID, CATEGORY[0]).toEntity()).toDomain()
        articleLikeQueryPersistenceAdapter = ArticleLikeQueryPersistenceAdapter(articleLikeJpaRepository)
    }

    @Nested
    @DisplayName("findByUserIdAndArticleOrNull 메소드는")
    inner class FindByUserIdAndArticleOrNull {

        @Test
        @DisplayName("ArticleLike가 DB에 존재하면 반환한다.")
        fun success_returns_domain_when_exist() {
            //given
            articleLikeJpaRepository.save(ArticleLike(
                userId = UserId(USER_ID),
                article = persistedArticle
            ).toEntity())

            //when
            val articleLike =
                articleLikeQueryPersistenceAdapter.findByUserIdAndArticleOrNull(USER_ID, persistedArticle)

            //then
            assertNotNull(articleLike)
            assertEquals(articleLike!!.article, persistedArticle)
        }

        @Test
        @DisplayName("ArticleLike가 DB에 존재하지 않으면 null을 반환한다.")
        fun success_returns_null_when_no_exist() {
            //when
            val articleLike =
                articleLikeQueryPersistenceAdapter.findByUserIdAndArticleOrNull(USER_ID, persistedArticle)

            //then
            assertNull(articleLike)
        }
    }
}