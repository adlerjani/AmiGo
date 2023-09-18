package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import hu.AmiGo.controller.dto.LikeResponseDto
import hu.AmiGo.controller.dto.PostResponseDto
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "amigo_like")
class Like (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column val createdDate: Timestamp = Timestamp.from(Instant.now()),
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @JsonBackReference var userId: User? = null,
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id") @JsonBackReference var postId: Post? = null,
    @Column val postId:Int=0,

    )

fun Like.toResponseDto(): LikeResponseDto {
    return LikeResponseDto(id,userId,postId,createdDate)
}