package hu.AmiGo.service

import com.AmiGo.extras.toNullable
import hu.AmiGo.controller.dto.CreateFriendRequestDto
import hu.AmiGo.controller.dto.FriendResponseDto
import hu.AmiGo.controller.dto.toFriend
import hu.AmiGo.model.Friend
import hu.AmiGo.model.FriendStatus
import hu.AmiGo.model.User
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.FriendRepository
import hu.AmiGo.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FriendService(
    private val friendRepository: FriendRepository,
    private val userRepository: UserRepository,
    private val log: Logger = LoggerFactory.getLogger(FriendService::class.java),
    )
{
    fun addFriendRequest(user1: User, userId2: Int): FriendResponseDto{
        val user2=userRepository.findById(userId2).toNullable();
        val requestDto = CreateFriendRequestDto(
            User(0,"","","","","","","", mutableListOf(), mutableListOf()),
            User(0,"","","","","","","", mutableListOf(), mutableListOf()),
            )
        requestDto.userId1=user1
        requestDto.userId2=user2

        val find = friendRepository.findFriendByUserId1AndUserId2(user1, user2!!)
        if (find != null&&find.userId1==requestDto.userId1 && find.userId2==requestDto.userId2){
            return throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }

        log.info("Friend request created by ${requestDto.userId1!!.id} with: ${requestDto.userId2?.id}")
        return friendRepository.save(requestDto.toFriend()).toResponseDto();
    }

    fun getAllFriendRequest(user:User):List<Friend>{
        return friendRepository.getAllByUserId2(user);
    }

    fun acceptFriendRequest(user1:User,userId2:Int,status:String):FriendResponseDto{
        print("ASDASD:" + userId2)
        val user2=userRepository.findById(userId2).toNullable();
        if (user2 != null) {
            print("névgeci:" + user2.username)
        }

        //TODO: csekkolni a kért ajánlatokat
        val findRequest=friendRepository.findFriendByUserId1AndUserId2(user1, user2!!) ?: return throw throw ResponseStatusException(HttpStatus.NOT_FOUND)

        if (status=="accepted"){
            findRequest.status=FriendStatus.ACCEPTED
        }else{
            findRequest.status=FriendStatus.DECLINED
        }

        return friendRepository.save(findRequest).toResponseDto()
    }
}