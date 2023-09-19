package hu.AmiGo.service

import com.AmiGo.extras.toNullable
import hu.AmiGo.controller.dto.CreatePostRequestDto
import hu.AmiGo.controller.dto.PostResponseDto
import hu.AmiGo.controller.dto.toPost
import hu.AmiGo.controller.dto.toUser
import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.PostRepository
import hu.AmiGo.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Objects.isNull

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val log: Logger = LoggerFactory.getLogger(PostService::class.java),
) {
    fun getPostById(id:Int): Post {
        return postRepository.getReferenceById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun savePost(user: User, requestDto: CreatePostRequestDto): PostResponseDto{
        requestDto.userId = user

        val post = postRepository.save(requestDto.toPost())
        log.info("Post created: ${post.id}")
        return post.toResponseDto();
    }


    fun getAllPostByUserId(id:Int): List<Post>{
        val user = userRepository.findById(id).toNullable() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return postRepository.findAllByUserId(user).toList()

    }


}