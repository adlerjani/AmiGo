package hu.AmiGo.repository

import hu.AmiGo.model.Like
import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository: JpaRepository<Like, Int> {
    fun findLikeByUserIdAndPostId(userId: User, postId: Int): Like?
}