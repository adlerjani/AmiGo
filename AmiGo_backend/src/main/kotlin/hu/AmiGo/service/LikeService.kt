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
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

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
        val find = likeRepository.findLikeByUserIdAndPostId(user,postId)
        if (find != null&&find.postId==requestDto.postId && find.userId==requestDto.userId){
            return throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }

        val like = likeRepository.save(requestDto.toLike())
        log.info("Post liked: ${requestDto.postId} by user: ${requestDto.userId}")
        return like.toResponseDto();
    }

    fun unlikePost(postId: Int): Any {
        val like = likeRepository.findLikeByPostId(postId)

////        val like = likeRepository.findById(postId)
//        val likes= likeRepository.findAll();
//        val iterator: MutableIterator<Like> = likes.iterator()
//        while (iterator.hasNext()) {
//            if (iterator.next() == like) {
//                iterator.remove()
//            }
//        }
//        likeRepository.saveAll(likes)
//        log.info("Like removed: ${like.id}")
        if (like != null) {
            return likeRepository.removeLikeById(like.id)
        };
        return throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
    }

}