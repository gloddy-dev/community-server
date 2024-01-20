package gloddy.persistence.util.mapper

import gloddy.article.port.`in`.dto.read.ArticleDetailUnit
import gloddy.category.port.`in`.dto.CategoryGetResponse
import gloddy.core.dto.PageResponse
import gloddy.core.util.toResponse
import gloddy.persistence.article.repository.read.ArticleDetailData
import org.springframework.data.domain.Page

fun Page<ArticleDetailData>.toResponse(userId: Long): PageResponse<ArticleDetailUnit> =
    this.map { ArticleDetailUnit(
        id = it.id,
        userId = it.userId,
        isWriter = it.userId == userId,
        isLiked = it.articleLikeId != null,
        category = CategoryGetResponse(
            id = it.categoryId,
            name = it.categoryName
        ),
        title = it.title,
        content = it.content,
        thumbnail = it.images?.firstOrNull(),
        images = it.images,
        likeCount = it.likeCount,
        commentCount = it.commentCount,
        createdAt = it.createdAt.toResponse()
    ) }.let { PageResponse(
        totalCount = it.totalElements,
        currentCount = it.numberOfElements,
        totalPage = it.totalPages,
        currentPage = it.number,
        contents = it.content
    ) }