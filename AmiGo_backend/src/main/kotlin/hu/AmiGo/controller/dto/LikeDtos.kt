package hu.AmiGo.controller.dto

import hu.AmiGo.model.Like
import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.sql.Timestamp

data class CreateLikeRequestDto(
    @field:NotBlank @field:Positive var userId: User?,
    @field:NotBlank @field:Positive var postId: Post?,
)

data class LikeResponseDto(
    val id:Int,
    val userId: User?,
    val postId: Post?,
    val createdDate: Timestamp,
)

fun CreateLikeRequestDto.toLike(): Like {
    return Like(userId = this.userId, postId = this.postId)
}