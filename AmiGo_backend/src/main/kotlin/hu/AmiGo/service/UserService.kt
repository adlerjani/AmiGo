package hu.AmiGo.service

import hu.AmiGo.controller.dto.AuthUserRequestDto
import hu.AmiGo.controller.dto.CreateUserRequestDto
import hu.AmiGo.controller.dto.UserResponseDto
import hu.AmiGo.controller.dto.toUser
import hu.AmiGo.model.toResponseDto
import hu.AmiGo.repository.UserRepository
import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import jakarta.servlet.http.Cookie
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.ZoneOffset
import java.time.ZonedDateTime


@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun saveUser(requestDto: CreateUserRequestDto): UserResponseDto {
        val user = userRepository.save(requestDto.toUser())
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

        //------

//        val issuer = user.username
////        val key:SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
////        val base64Key= Encoders.BASE64.encode(key.getEncoded());
//        val jwt = Jwts.builder()
//            .setIssuer(issuer)
//            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //1 day
//            .signWith(SignatureAlgorithm.ES512,"secret").compact()

//        return findUser.toResponseDto();

        return encodedJWT;
    }

}
