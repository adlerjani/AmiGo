package hu.AmiGo.controller.dto

import hu.AmiGo.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


data class CreateUserRequestDto (
    @field:NotBlank val username:String,
    @field:NotBlank val password:String,
    @field:NotBlank val full_name:String,
    @field:NotBlank @field:Email val email:String,
)

data class AuthUserRequestDto (
    @field:NotBlank val username:String,
    @field:NotBlank val password:String,
)

data class UserResponseDto (
    val full_name: String,
    val username: String,
    val email: String,
    val password: String,
    val profileURL: String,
    val birth: String,
    val bio: String,
)

fun CreateUserRequestDto.toUser(): User {
    val passwordEncoder = BCryptPasswordEncoder()
    return User(
        username=this.username,
        password=passwordEncoder.encode(this.password),
        full_name=this.full_name,
        email = this.email,
    )
}