package hu.AmiGo.controller.dto

import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.sql.Timestamp

data class CreatePostRequestDto(
    @field:NotBlank val contentHeadline:String,
    @field:NotBlank val content:String,
//    @field:NotBlank val created_date:Timestamp,
    @field:NotBlank @field:Positive var userId: User?,
)

data class PostResponseDto(
    val contentHeadline: String,
    val content: String,
    val createdDate: Timestamp,
    val userId: User?,
)

fun CreatePostRequestDto.toPost(): Post {
    return Post(contentHeadline = this.contentHeadline, content = this.content, userId = this.userId)
}