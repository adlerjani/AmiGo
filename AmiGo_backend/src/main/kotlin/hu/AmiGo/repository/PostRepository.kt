package hu.AmiGo.repository

import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Int> {

//    fun findAllByUser_id(id:Int):List<Post>
    fun findAllByUserId(userId: User):List<Post>

}