package hu.AmiGo.controller

import hu.AmiGo.controller.dto.CreateFriendRequestDto
import hu.AmiGo.service.FriendService
import hu.AmiGo.service.UserService
import io.fusionauth.jwt.Verifier
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACVerifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/")
class FriendController (
    private val friendService: FriendService,
    private val userService: UserService
)
{
    @PostMapping("/request/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun postAddFriendRequest(@CookieValue("jwt") jwt:String?,  @PathVariable id: Int):Any{
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)
        val user=userService.getUserByUsernameForAuth(encode_jwt.issuer)

        return  friendService.addFriendRequest(user,id)
    }

    @GetMapping("/request")
    fun getFriendRequests(@CookieValue("jwt") jwt:String?):Any{
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)
        val user=userService.getUserByUsernameForAuth(encode_jwt.issuer)

        return friendService.getAllFriendRequest(user)
    }

    @PostMapping("/request-answer/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun postReactFriendRequest(@CookieValue("jwt") jwt:String?,  @PathVariable id: Int, @RequestParam status:String):Any{
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)
        val user=userService.getUserByUsernameForAuth(encode_jwt.issuer)

        return  friendService.acceptFriendRequest(user,id,status)
    }
}