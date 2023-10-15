package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import hu.AmiGo.controller.dto.UserResponseDto
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
private var asd = "";

@Entity
@Table(name = "amigo_user")
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
    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL], orphanRemoval = true) val posts:MutableList<Post> = mutableListOf(),
    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL], orphanRemoval = true) val likes:MutableList<Like> = mutableListOf(),
    @OneToMany(mappedBy = "userId1", cascade = [CascadeType.ALL], orphanRemoval = true) val friend1:MutableList<Friend> = mutableListOf(),
    @OneToMany(mappedBy = "userId2", cascade = [CascadeType.ALL], orphanRemoval = true) val friend2:MutableList<Friend> = mutableListOf(),

)

fun User.toResponseDto(): UserResponseDto {
    return UserResponseDto(id,full_name,username,email,password,profileURL,birth,bio)
}
