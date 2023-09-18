package hu.AmiGo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "post")
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column val content_headline: String = "",
    @Column val content: String = "",
    @Column val created_date: Timestamp = Timestamp.from(Instant.now()),
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user") @JsonBackReference val user_id: User

)