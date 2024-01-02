package gloddy.comment.port.out

import gloddy.comment.dto.readModel.CommentParentInfoDto
import gloddy.comment.dto.readModel.CommentRefDto


interface CommentGetPort {
    fun getMaxRef(): CommentRefDto
    fun getParentRef(): CommentParentInfoDto
}