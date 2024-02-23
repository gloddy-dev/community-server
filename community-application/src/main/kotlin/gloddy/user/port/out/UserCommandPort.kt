package gloddy.user.port.out

import gloddy.user.User

interface UserCommandPort {
    fun save(user: User): User
}