package hu.AmiGo.controller

import hu.AmiGo.controller.dto.CreateLikeRequestDto
import hu.AmiGo.controller.dto.CreatePostRequestDto
import hu.AmiGo.model.User
import hu.AmiGo.service.LikeService
import hu.AmiGo.service.PostService
import hu.AmiGo.service.UserService
import io.fusionauth.jwt.Verifier
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACVerifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/like/")
class LikeController(
    private val likeService: LikeService,
    private val userService: UserService
)
{
    @PostMapping("/{id}")
    fun postCreatePost(@CookieValue("jwt") jwt:String?, @PathVariable id: Int):Any{
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)
        val user=userService.getUserByUsernameForAuth(encode_jwt.issuer)


        return likeService.likePost(id,user);
    }
}