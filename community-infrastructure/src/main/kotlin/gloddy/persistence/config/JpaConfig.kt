package gloddy.persistence.config

<<<<<<< HEAD
=======

>>>>>>> 87ab656 (feat: Article 페이징 쿼리 작성)
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing
class JpaConfig(
    @PersistenceContext
    private val em: EntityManager
) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory =
        JPAQueryFactory(em)
}