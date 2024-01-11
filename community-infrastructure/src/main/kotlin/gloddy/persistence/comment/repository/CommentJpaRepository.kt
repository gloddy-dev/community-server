package gloddy.persistence.comment.repository

import gloddy.persistence.comment.CommentJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<CommentJpaEntity, Long>, CommentJpaCustomRepository {
}