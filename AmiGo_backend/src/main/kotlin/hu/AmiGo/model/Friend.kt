package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import hu.AmiGo.controller.dto.FriendResponseDto
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "amigo_friend")
class Friend (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column val createdDate: Timestamp = Timestamp.from(Instant.now()),
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user1_id") @JsonBackReference var userId1: User? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user2_id") @JsonBackReference var userId2: User? = null,
    @Enumerated(EnumType.STRING) @Column var status: FriendStatus = FriendStatus.PENDING
    )

fun Friend.toResponseDto():FriendResponseDto{
    return FriendResponseDto(id,userId1,userId2,createdDate,status)
}