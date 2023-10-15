package hu.AmiGo.repository

import hu.AmiGo.model.Friend
import hu.AmiGo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository: JpaRepository<Friend, Int>{
    fun getAllByUserId2(userId2: User):List<Friend>

    fun findFriendByUserId1AndUserId2(userId1:User, userId2: User):Friend?
}