package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import hu.AmiGo.controller.dto.PostResponseDto
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "amigo_post")
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column val contentHeadline: String = "",
    @Column val content: String = "",
    @Column val createdDate: Timestamp = Timestamp.from(Instant.now()),
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @JsonBackReference var userId: User? = null,
    @OneToMany(mappedBy = "postId", cascade = [CascadeType.ALL], orphanRemoval = true) val like:MutableList<Like> = mutableListOf()

)

fun Post.toResponseDto(): PostResponseDto{
    return PostResponseDto(contentHeadline,content,createdDate,userId)
}