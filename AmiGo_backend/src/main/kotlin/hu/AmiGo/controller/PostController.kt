package hu.AmiGo.controller

import hu.AmiGo.controller.dto.CreatePostRequestDto
import hu.AmiGo.controller.dto.PostResponseDto
import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import hu.AmiGo.service.PostService
import hu.AmiGo.service.UserService
import io.fusionauth.jwt.Verifier
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACVerifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/")
class PostController(
    private val postService: PostService,
    private val userService: UserService
)
{
    @PostMapping("/create")
    fun postCreatePost(@CookieValue("jwt") jwt:String?,@RequestBody post:CreatePostRequestDto):Any{
        if (jwt == null){
            return "Authentication expired"
        }
        val verifier: Verifier = HMACVerifier.newVerifier("too many secrets")
        val encode_jwt = JWT.getDecoder().decode(jwt, verifier)
        val user=userService.getUserByUsernameForAuth(encode_jwt.issuer)

        return postService.savePost(user,post);
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Int):Post{
        return postService.getPostById(id)
    }

    @GetMapping("/user/{id}")
    fun getAllPostByUserId(@PathVariable id: Int):List<Post>{
        return postService.getAllPostByUserId(id);
    }


}