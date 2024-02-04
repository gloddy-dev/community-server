package gloddy.user.service

import gloddy.user.port.`in`.dto.UserSaveRequest
import gloddy.user.port.`in`.dto.toDomain
import gloddy.user.port.out.UserCommandPort
import org.springframework.stereotype.Service

@Service
class UserCommandService(
    private val userCommandPort: UserCommandPort
) {

    fun save(request: UserSaveRequest) {
        userCommandPort.save(request.toDomain())
    }
}