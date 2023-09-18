package hu.AmiGo.service

import com.AmiGo.extras.toNullable
import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.controller.dto.toUser
import hu.AmiGo.model.User
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.UserRepository
import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import jakarta.servlet.http.Cookie
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.ZoneOffset
import java.time.ZonedDateTime


@Service
class UserService(
    private val userRepository: UserRepository,
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java),

    ) {

    fun saveUser(requestDto: CreateUserRequestDto): UserResponseDto {
        val user = userRepository.save(requestDto.toUser())
        log.info("User created: ${user.id}")
        return user.toResponseDto();
    }

    fun getUserByUsername(user: AuthUserRequestDto): String {
        val findUser = userRepository.findByUsername(user.username)  ?: throw ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!BCryptPasswordEncoder().matches(user.password,findUser.password)){
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }

        val signer: Signer = HMACSigner.newSHA256Signer("too many secrets")
        val jwt = JWT().setIssuer(user.username)
            .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
            .setSubject("f1e33ab3-027f-47c5-bb07-8dd8ab37a2d3")
            .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusDays(1));

        val encodedJWT = JWT.getEncoder().encode(jwt, signer)
        log.info("User logged: ${findUser.id}")
        return encodedJWT;
    }

    fun getUserByUsernameForAuth(username:String): User {
        return userRepository.findByUsername(username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND);
    }

//    fun getUserIdByUsernameForAuth(username:String): User {
//        val user = return userRepository.findByUsername(username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND);
//        return user
//    }

}
