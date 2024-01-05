package gloddy.persistence.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseTimeEntity(
    @Column(name = "created_at")
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @Column(name = "updated_at")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
) {
}