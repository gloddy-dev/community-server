package gloddy.persistence.user.adapter

import gloddy.persistence.user.repository.UserJpaRepository
import gloddy.persistence.util.mapper.toDomain
import gloddy.persistence.util.mapper.toEntity
import gloddy.user.User
import gloddy.user.port.out.UserCommandPort
import org.springframework.stereotype.Component

@Component
class UserCommandAdapter(
    private val userJpaRepository: UserJpaRepository,
) : UserCommandPort {

    override fun save(user: User): User {
        return userJpaRepository.save(user.toEntity()).toDomain()
    }
}