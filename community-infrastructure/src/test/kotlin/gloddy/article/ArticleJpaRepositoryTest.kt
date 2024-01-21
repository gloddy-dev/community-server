package gloddy.article

import gloddy.ArticleFixture
import gloddy.CategoryFixture
import gloddy.PersistenceTest
import gloddy.article.port.`in`.ArticleOrder
import gloddy.persistence.article.ArticleLikeJpaEntity
import gloddy.persistence.category.CategoryJpaEntity
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

@DisplayName("ArticleJpaRepository 클래스의")
class ArticleJpaRepositoryTest : PersistenceTest(){

    @DisplayName("findDetailArticlePageByCategoryId 메소드는")
    @Nested
    inner class FindDetailArticlePageByCategoryId {

        private val USER_ID = 99L
        private val PAGE_SIZE = 10
        private lateinit var kpopCategory: CategoryJpaEntity
        private lateinit var languageCategory: CategoryJpaEntity
        private lateinit var qnaCategory: CategoryJpaEntity

        @BeforeEach
        fun setUp() {
            val categoryJpaEntities = categoryJpaRepository.findAll()
            kpopCategory = categoryJpaEntities.firstOrNull { it.name == CategoryFixture.K_POP.names}!!
            languageCategory = categoryJpaEntities.firstOrNull { it.name == CategoryFixture.LANGUAGE.names }!!
            qnaCategory = categoryJpaEntities.firstOrNull { it.name == CategoryFixture.QNA.names }!!
        }

        @Test
        @DisplayName("categoryId = null, order = LATEST 값을 입력받으면 전체 카테고리에 대한 페이지를 최신순으로 반환한다.")
        fun success_when_categoryId_is_null_and_latest() {

            //given
            for (i in 1..30) {
                ArticleFixture.HAVE_IMAGE.toDomain(USER_ID, kpopCategory.toDomain())
                    .run {  articleJpaRepository.save(this.toEntity())}
            }

            for (i in 1..30) {
                ArticleFixture.HAVE_IMAGE.toDomain(USER_ID, languageCategory.toDomain())
                    .run { articleJpaRepository.save(this.toEntity()) }
            }
            //when
            val pageRequest = PageRequest.of(0, PAGE_SIZE)
            val articlePage = articleJpaRepository.findDetailArticlePageByCategoryId(
                categoryId = null,
                userId = USER_ID,
                pageable = pageRequest,
                order = ArticleOrder.LATEST
            )

            //then
            assertEquals(articlePage.content.size, 10)
            assertEquals(articlePage.totalElements, 60)
        }

        @Test
        @DisplayName("categoryId = ?, order = LATEST 값을 입력 받으면 그 카테고리에 대한 페이지를 최신순으로 반환한다.")
        fun success_when_categoryId_Is_specific() {

            //given
            for (i in 1..30) {
                ArticleFixture.HAVE_IMAGE.toDomain(USER_ID, kpopCategory.toDomain())
                    .run {  articleJpaRepository.save(this.toEntity())}
            }

            //when
            val pageRequest = PageRequest.of(0, PAGE_SIZE)
            val articlePage = articleJpaRepository.findDetailArticlePageByCategoryId(
                categoryId = kpopCategory.id!!,
                userId = USER_ID,
                pageable = pageRequest,
                order = ArticleOrder.LATEST
            )

            //then
            assertEquals(articlePage.content.size, 10)
            assertEquals(articlePage.totalElements, 30)
        }

        @Test
        @DisplayName("특정 게시글이 자신이 좋아요한 게시글이 아니면 articleLikeId 값이면 null이다.")
        fun success_returns_article_like_id() {

            val LIKE_USER_ID = 9999L

            //given
            val likedArticle = ArticleFixture.HAVE_IMAGE.toDomain(USER_ID, kpopCategory.toDomain())
                .let { articleJpaRepository.save(it.toEntity()) }
            ArticleFixture.HAVE_IMAGE.toDomain(USER_ID, kpopCategory.toDomain())
                .run {  articleJpaRepository.save(this.toEntity())}
            val articleLike = ArticleLikeJpaEntity(
                userId = LIKE_USER_ID,
                article = likedArticle
            ).let { articleLikeJpaRepository.save(it) }

            //when
            val pageRequest = PageRequest.of(0, PAGE_SIZE)
            val articlePage = articleJpaRepository.findDetailArticlePageByCategoryId(
                categoryId = kpopCategory.id!!,
                userId = LIKE_USER_ID,
                pageable = pageRequest,
                order = ArticleOrder.LATEST
            )

            //then
            assertNull(articlePage.content[0].articleLikeId)
            assertNotNull(articlePage.content[1].articleLikeId)
            assertEquals(articlePage.content[1].articleLikeId!!, articleLike.id!!)
        }
    }
}