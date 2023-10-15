package hu.AmiGo.controller.dto

import hu.AmiGo.model.Friend
import hu.AmiGo.model.FriendStatus
import hu.AmiGo.model.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.sql.Timestamp

data class CreateFriendRequestDto(
    @field:NotBlank @field:Positive var userId1: User?,
    @field:NotBlank @field:Positive var userId2: User?,
)

data class FriendResponseDto(
    val id:Int,
    val userId1: User?,
    val userId2: User?,
    val createdDate: Timestamp,
    val status: FriendStatus
)

fun CreateFriendRequestDto.toFriend(): Friend{
    return Friend(userId1=this.userId1, userId2 = this.userId2)
}