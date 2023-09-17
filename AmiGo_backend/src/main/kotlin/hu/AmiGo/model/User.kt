package hu.AmiGo.model

import hu.AmiGo.controller.dto.UserResponseDto
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
private var asd = "";

@Entity
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column(unique = true)
    val username:String="",
    @Column val profileURL:String ="",
    @Column val full_name:String ="",
    @Column val email:String ="",
    @Column val birth:String="",
    @Column val bio:String ="",
    @Column var password:String ="",

    )

fun User.toResponseDto(): UserResponseDto {
    return UserResponseDto(full_name,username,email,password,profileURL,birth,bio)
}
