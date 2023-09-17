package hu.AmiGo.controller

import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.service.UserService
import io.fusionauth.jwt.domain.JWT
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

//@CrossOrigin
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
    fun postLoginUser(@RequestBody user: AuthUserRequestDto, response: HttpServletResponse): String {
        var cookie = Cookie("jwt", userService.getUserByUsername(user))
        cookie.isHttpOnly = true
        response.addCookie(cookie)
        return "Success"
    }

}