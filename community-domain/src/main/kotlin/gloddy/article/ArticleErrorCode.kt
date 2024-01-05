package gloddy.article

import gloddy.core.ErrorCode

enum class ArticleErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val message: String
) : ErrorCode {
    NOT_FOUND(400, "ARTICLE_001", "해당 게시글을 찾을 수 없습니다."),
    IMAGE_SIZE_OVER(400, "ARTICLE_002", "게시글에 이미지는 최대 3개 입니다."),
    NO_AUTHORIZATION(401, "ARTICLE_003", "해당 게시글에 권한이 없습니다.")
}