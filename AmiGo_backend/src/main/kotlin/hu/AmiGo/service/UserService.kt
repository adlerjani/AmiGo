package hu.AmiGo.service

import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.controller.dto.toUser
import hu.AmiGo.model.User
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun saveUser(requestDto: CreateUserRequestDto): UserResponseDto {
        val user = userRepository.save(requestDto.toUser())
        return user.toResponseDto();
    }

    fun getUserByUsername(user: AuthUserRequestDto): UserResponseDto {
        val findUser = userRepository.findByUsername(user.username)  ?: throw ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!BCryptPasswordEncoder().matches(user.password,findUser.password)){
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }

//        val issuer = user.username
//        val jwt = Jwts.builder()
        return findUser.toResponseDto();
    }

}
