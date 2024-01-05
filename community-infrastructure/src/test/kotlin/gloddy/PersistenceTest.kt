package gloddy

import gloddy.category.Category
import gloddy.persistence.article.repository.ArticleJpaRepository
import gloddy.persistence.category.repository.CategoryJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
abstract class PersistenceTest {

    protected lateinit var CATEGORY: List<Category>
    @Autowired
    protected lateinit var categoryJpaRepository: CategoryJpaRepository
    @Autowired
    protected lateinit var articleJpaRepository: ArticleJpaRepository
    @Autowired
    protected lateinit var em: EntityManager

    @BeforeEach
    fun init() {
        val categories = categoryJpaRepository.saveAll(
            CategoryFixture.values().map {
                it.toDomain().toEntity()
            }
        )
        CATEGORY = categories.map { it.toDomain() }
        flushAndClear()
    }

    protected fun flushAndClear() {
        em.flush()
        em.clear()
    }
}