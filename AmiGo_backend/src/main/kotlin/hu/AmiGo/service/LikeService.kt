package hu.AmiGo.service

import com.AmiGo.extras.toNullable
import hu.AmiGo.controller.dto.*
import hu.AmiGo.model.Like
import hu.AmiGo.model.User
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.LikeRepository
import hu.AmiGo.repository.PostRepository
import hu.AmiGo.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val log: Logger = LoggerFactory.getLogger(LikeService::class.java),
)
{
    fun likePost(postId:Int,user: User): LikeResponseDto{
        val requestDto = CreateLikeRequestDto(User(0,"","","","","","","", mutableListOf(), mutableListOf()),0)


        val post = postRepository.findById(postId).toNullable();
        if (post != null) {
            requestDto.postId=post.id
            requestDto.userId=user
        }
        val like = likeRepository.save(requestDto.toLike())
        log.info("Post liked: ${like.id}")
        return like.toResponseDto();
    }
}