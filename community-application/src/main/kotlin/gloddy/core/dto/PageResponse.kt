package gloddy.core.dto

data class PageResponse<T>(
    val totalCount: Long,
    val currentCount: Int,
    val totalPage: Int,
    val currentPage: Int,
    val contents: List<T>
)