package gloddy.persistence.user.repository

import gloddy.persistence.user.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserJpaEntity, Long> {
}