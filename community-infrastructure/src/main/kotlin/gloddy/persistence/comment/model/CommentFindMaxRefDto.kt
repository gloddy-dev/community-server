package gloddy.persistence.comment.model

import com.querydsl.core.annotations.QueryProjection
import gloddy.comment.dto.readModel.CommentFindMaxRefDto as CommentFindMaxRefResponseDto

data class CommentFindMaxRefDto @QueryProjection constructor(
    val maxRef: Int
)

fun CommentFindMaxRefDto.toResponseDto(): CommentFindMaxRefResponseDto =
    CommentFindMaxRefResponseDto(
        maxRef = this.maxRef
    )