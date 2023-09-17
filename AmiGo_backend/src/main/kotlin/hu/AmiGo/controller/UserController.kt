package hu.AmiGo.controller

import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/user/")
public class UserController (
    private val userService: UserService
)
{
    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun postRegisterUser(@RequestBody user: CreateUserRequestDto): UserResponseDto {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    fun postLoginUser(@RequestBody user: AuthUserRequestDto): UserResponseDto{
        return userService.getUserByUsername(user)
    }

}