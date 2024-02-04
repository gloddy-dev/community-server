package gloddy.core

enum class ErrorCode(
    val statusCode: Int,
    val errorCode: String,
    val message: String
) {

    // Comment
    COMMENT_NOT_FOUND(404, "COMMENT_001", "해당 댓글을 찾을 수 없습니다."),
    COMMENT_NOT_AUTHORIZATION(401, "COMMENT_002", "해당 댓글에 권한이 없습니다."),
    COMMENT_LIKE_NOT_FOUND(404, "COMMENT_003", "존재하지 않는 댓글 좋아요입니다."),

    // Article
    ARTICLE_NOT_FOUND(404, "ARTICLE_001", "해당 게시글을 찾을 수 없습니다."),
    ARTICLE_IMAGE_SIZE_OVER(400, "ARTICLE_002", "게시글에 이미지는 최대 3개 입니다."),
    ARTICLE_NO_AUTHORIZATION(401, "ARTICLE_003", "해당 게시글에 권한이 없습니다."),

    // Category
    CATEGORY_NOT_FOUND(404, "CATEGORY_001", "해당 카테고리를 찾을 수 없습니다."),

    // User
    USER_NOT_FOUND(404, "COMMUNITY_USER_001", "해당 유저를 찾을 수 없습니다.")
}