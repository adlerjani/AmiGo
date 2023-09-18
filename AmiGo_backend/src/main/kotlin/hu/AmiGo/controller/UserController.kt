package hu.AmiGo.controller

import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.model.User
import hu.AmiGo.service.UserService
import io.fusionauth.jwt.Verifier
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACVerifier
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

    @GetMapping("/auth")
    fun getAuthUser(@CookieValue("jwt") jwt:String?): Any {
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)

        return userService.getUserByUsernameForAuth(encode_jwt.issuer)
//        return encode_jwt.issuer
    }

    @PostMapping("/logout")
    fun postLogoutUser(response: HttpServletResponse): String{
        val cookie = Cookie("jwt","")
        cookie.maxAge=0

        response.addCookie(cookie)
        return "Logged Out";
    }

}