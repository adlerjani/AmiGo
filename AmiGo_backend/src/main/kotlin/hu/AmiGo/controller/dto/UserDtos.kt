package hu.AmiGo.controller.dto

import com.AmiGo.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class CreateUserRequestDto (
    @field:NotBlank val password:String,
    @field:NotBlank val full_name:String,
    @field:NotBlank @field:Email val email:String,
)

data class UserResponseDto (
    val full_name: String,
    val email: String,
    val profileURL: String,
    val birth: String,
    val bio: String,
)

fun CreateUserRequestDto.toUser():User{
    return User(
        full_name=this.full_name,
        email = this.email,
    )
}