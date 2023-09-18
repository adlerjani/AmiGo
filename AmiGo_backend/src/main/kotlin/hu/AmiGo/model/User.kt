package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @Column @JsonIgnore var password:String ="",
    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL], orphanRemoval = true) val posts:MutableList<Post> = mutableListOf()

    )

fun User.toResponseDto(): UserResponseDto {
    return UserResponseDto(id,full_name,username,email,password,profileURL,birth,bio)
}
