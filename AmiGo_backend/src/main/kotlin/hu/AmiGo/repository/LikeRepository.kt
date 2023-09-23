package hu.AmiGo.repository

import hu.AmiGo.model.Like
import hu.AmiGo.model.Post
import hu.AmiGo.model.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository: JpaRepository<Like, Int> {
    fun findLikeByUserIdAndPostId(userId: User, postId: Int): Like?

    fun findLikeByPostId(postId:Int):Like?

//    @Query("DELETE FROM 'amigo_like' WHERE id=:id")
//    @Query("DELETE FROM \"amigo_like\" WHERE id=:id")
@Modifying
@Transactional
@Query("delete from Like l where l.id=:id")
    fun removeLikeById(@Param("id")id:Int):Any

}